/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterfaceTier.controllers;

import uiExceptions.TextEmptyException;
import clientBusinessLogic.ClientFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logicalModel.model.User;
import uiExceptions.WrongEmailFormatException;

/**
 *
 * @author 2dam
 */
public class SignInController {

    public SignInController() {
    }

    @FXML
    private TextField emailText;

    @FXML
    private PasswordField pfPasswrd;

    @FXML
    private TextField tfPasswrd;

    @FXML
    private ToggleButton tgbtnEyeIcon;

    @FXML
    private ImageView ivEyeIcon;

    @FXML
    private Label lblError;

    @FXML
    private Button btnAccept;

    @FXML
    private Hyperlink hypSignUp;

    private Logger logger = Logger.getLogger(SignInController.class.getName());

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void initStage(Parent root) {
        try {
            logger.info("Initializizng Sign In stage");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Sign In");
            stage.setResizable(false);

            emailText.isFocused();
            ivEyeIcon.setImage(new Image("/resources/images/ShowPasswd.png"));
            lblError.setText("");
            btnAccept.setDefaultButton(true);

            tfPasswrd.setVisible(false); // Al inicio no es visible

            pfPasswrd.textProperty().addListener(this::passwrdIsVisible);
            tfPasswrd.textProperty().addListener(this::passwrdIsVisible);

            tgbtnEyeIcon.setOnAction(this::handelEyeIconToggleButtonAction);

            hypSignUp.setOnAction(this::handelSignUpHyperlink);

            stage.show();
        } catch (Exception e) {
            String errorMsg = "Error opening window:\n" + e.getMessage();
            logger.severe(errorMsg);
        }
    }

    public void passwrdIsVisible(ObservableValue observable, String oldValue, String newValue) {
        
        if (pfPasswrd.isVisible()) {
            tfPasswrd.setText(pfPasswrd.getText());

        } else if (tfPasswrd.isVisible()) {
            pfPasswrd.setText(tfPasswrd.getText());
        }
    }

    @FXML
    public void handelEyeIconToggleButtonAction(ActionEvent event) {
        if (tgbtnEyeIcon.isSelected()) {
            pfPasswrd.setVisible(false);
            tfPasswrd.setVisible(true);  // Mostrar el TextField (contraseña visible)
            // Cambiar el icono al de "contraseña visible"
            ivEyeIcon.setImage(new Image("/resources/images/HidePasswd.png"));
        } else {
            tfPasswrd.setVisible(false);
            pfPasswrd.setVisible(true);  // Ocultar el TextField (contraseña visible)
            // Cambiar el icono al de "contraseña oculta"
            ivEyeIcon.setImage(new Image("/resources/images/ShowPasswd.png"));
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws WrongEmailFormatException, IOException, TextEmptyException {
        String email = this.emailText.getText().trim();
        String passwrd = this.pfPasswrd.getText().trim();

        try {
            TextEmptyException.validateNotEmpty(email, passwrd);

            WrongEmailFormatException.validateEmail(email);

            User user = new User(email, passwrd);
            User userSignedIn = ClientFactory.getSignable().signIn(user); 

            //Abrir ventana Main
            emailText.setText("");
            tfPasswrd.setText("");
            pfPasswrd.setText("");
            lblError.setText("");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userInterfaceTier/view/MainWindowView.fxml"));
            Parent root = (Parent) loader.load();
            MainWindowController controller = ((MainWindowController) loader.getController());
            //controller.setStage(stage);
            //controller.initStage(root, userSignedIn);
        } catch (WrongEmailFormatException e) {
            lblError.setText(e.getMessage());
            logger.severe(e.getLocalizedMessage());
        } catch (TextEmptyException e) {
            lblError.setText(e.getMessage());
            logger.severe(e.getMessage());
        }
    }

    @FXML
    private void handelSignUpHyperlink(ActionEvent event) {
        try {
            //Abrir ventana SignUp
            emailText.setText("");
            tfPasswrd.setText("");
            pfPasswrd.setText("");
            lblError.setText("");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userInterfaceTier/view/SignUpView.fxml"));
            Parent root = (Parent) loader.load();
            SignUpController controller = ((SignUpController) loader.getController());
            //controller.setStage(stage);
            //controller.initStage(root);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
