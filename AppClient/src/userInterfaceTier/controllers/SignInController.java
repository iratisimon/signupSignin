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
import logicalExceptions.MaxThreadsErrorException;
import logicalExceptions.ServerErrorException;
import logicalExceptions.SignInErrorException;
import logicalExceptions.UserNotActiveException;
import logicalModel.model.User;
import uiExceptions.WrongEmailFormatException;

/**
 * The SignInController class manages the sign-in functionality of the application.
 * It handles user input for email and password, validates the input, and manages
 * the visibility of password fields. It also controls the navigation to other
 * application views.
 * 
 * This class utilizes JavaFX for the user interface and includes methods
 * to handle button actions, password visibility, and transitions between views.
 * 
 * @author Irati
 */
public class SignInController {
    /**
     * Constructs a new SignInController instance.
     */
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

     /**
     * Sets the stage for this controller.
     * 
     * @param stage the stage to be set.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    /**
     * Returns the current stage of the controller.
     * 
     * @return the current stage.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Initializes the stage with the specified root node.
     * 
     * @param root the root node for the scene.
     */
    public void initStage(Parent root) {
        try {
            logger.info("Initializizng Sign In stage");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            //El nombre de la ventana es “Sign In”.
            stage.setTitle("Sign In");
            //Ventana no redimensionable.
            stage.setResizable(false);
            Image icon = new Image(getClass().getResourceAsStream("/resources/images/catrina.png"));
            stage.getIcons().add(icon);
            //Se enfoca el campo Email dado que es el primer campo que el usuario deberá rellenar
            emailText.isFocused();
            //El icono del ToggleButton será el ojo que muestre la contraseña.
            ivEyeIcon.setImage(new Image("/resources/images/ShowPasswd.png"));
            lblError.setText("");
            btnAccept.setDefaultButton(true);
            //El usuario ingresa la contraseña en el pfPassword. 
            tfPasswrd.setVisible(false); // Al inicio no es visible

            pfPasswrd.textProperty().addListener(this::textPropertyChange);
            tfPasswrd.textProperty().addListener(this::textPropertyChange);

            tgbtnEyeIcon.setOnAction(this::handelEyeIconToggleButtonAction);

            hypSignUp.setOnAction(this::handelSignUpHyperlink);

            stage.show();
        } catch (Exception e) {
            String errorMsg = "Error opening window:\n" + e.getMessage();
            logger.severe(errorMsg);
        }
    }

    /**
     * Updates the visibility of the password fields based on user interaction.
     * 
     * @param observable the observable value.
     * @param oldValue the old value of the password field.
     * @param newValue the new value of the password field.
     */
    public void textPropertyChange(ObservableValue observable, String oldValue, String newValue) {
        //La contraseña ingresada en el campo pfPasswrd se replica en el campo de texto tfPasswrd.
        if (pfPasswrd.isVisible()) {
            tfPasswrd.setText(pfPasswrd.getText());
        //Si el campo que esta visible es el tfPasswrd entonces la contraseña se replicará en el campo pfPasswrd.
        } else if (tfPasswrd.isVisible()) {
            pfPasswrd.setText(tfPasswrd.getText());
        }
    }

     /**
     * Handles the action of the eye icon toggle button.
     * 
     * @param event the action event triggered by the button.
     */
    @FXML
    public void handelEyeIconToggleButtonAction(ActionEvent event) {
        if (tgbtnEyeIcon.isSelected()) {
            pfPasswrd.setVisible(false); // Ocultar el PasswordField
            tfPasswrd.setVisible(true);  // Mostrar el TextField (contraseña visible)
            // Cambiar el icono al de "contraseña visible"
            ivEyeIcon.setImage(new Image("/resources/images/HidePasswd.png"));
        } else {
            tfPasswrd.setVisible(false); // Mostrar el PasswordField
            pfPasswrd.setVisible(true);  // Ocultar el TextField (contraseña visible)
            // Cambiar el icono al de "contraseña oculta"
            ivEyeIcon.setImage(new Image("/resources/images/ShowPasswd.png"));
        }
    }

    /**
     * Handles the action of the accept button.
     * Validates email and password input, then attempts to sign in the user.
     * 
     * @param event the action event triggered by the button.
     * @throws WrongEmailFormatException if the email format is incorrect.
     * @throws IOException if an input or output exception occurs.
     * @throws TextEmptyException if any input field is empty.
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws WrongEmailFormatException, IOException, TextEmptyException, MaxThreadsErrorException, ServerErrorException, SignInErrorException, UserNotActiveException {
      
        String email = this.emailText.getText().trim();
        String passwrd = this.pfPasswrd.getText().trim();

        
        try {
            // se valida que los campos email y contraseña estén rellenados, en caso contrario se informa al usuario de que debe rellenarlos para poder continuar mediante la excepción: “TextEmptyException”.
            TextEmptyException.validateNotEmpty(email, passwrd);
            // se valida que el email introducido tiene el formato válido, en caso contrario se informa al usuario mediante la excepción propia: “WrongEmailFormatException”. 
            WrongEmailFormatException.validateEmail(email);

            User user = new User(email, passwrd);
            //comprobar que existe + implementar excepciones
            User userSignedIn = ClientFactory.getSignable().signIn(user); 
            // Si los datos son correctos y el usuario se encuentra activo, se abrirá la ventana “MainWindowView” pasandole el user.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userInterfaceTier/view/MainWindowView.fxml"));
            Parent root = (Parent) loader.load();
            MainWindowController controller = ((MainWindowController) loader.getController());
            controller.setStage(stage);
            controller.initStage(root, userSignedIn);
        } catch (WrongEmailFormatException e) {
            lblError.setText(e.getMessage());
            logger.severe(e.getLocalizedMessage());
        } catch (TextEmptyException e) {
            lblError.setText(e.getMessage());
            logger.severe(e.getMessage());
        }
    }

    /**
     * Handles the action of the sign-up hyperlink.
     * Navigates to the sign-up view.
     * 
     * @param event the action event triggered by the hyperlink.
     */
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
