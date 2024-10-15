/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterfaceTier.controllers;

import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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

    private TextField tfPasswrd;

    @FXML
    private ToggleButton tgbtnEyeIcon;

    @FXML
    private ImageView ivEyeIcon;

    @FXML
    private Button btnAccept;

    private boolean isPasswordVisible = false;

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
            stage.setOnShowing(this::handleWindowShowing);
            stage.show();

            // Establecer el icono inicial (contraseña oculta)
            ivEyeIcon.setImage(new Image("/resources/images/HidePasswd.png"));

            // Crear el TextField que muestra la contraseña en texto visible
            tfPasswrd = new TextField();
            tfPasswrd.setManaged(false); // No afecta al layout cuando está oculto
            tfPasswrd.setVisible(false); // Al inicio no es visible

            // Vincular las propiedades de texto entre el PasswordField y el TextField
            tfPasswrd.textProperty().bindBidirectional(pfPasswrd.textProperty());

            // Añadir el ToggleButton para cambiar la visibilidad de la contraseña
            tgbtnEyeIcon.setOnAction(event -> passwrdIsVisible());

        } catch (Exception e) {
            String errorMsg = "Error opening window:\n" + e.getMessage();
            logger.severe(errorMsg);
        }
    }

    private void passwrdIsVisible() {
        if (!isPasswordVisible) {
            // Ocultar el PasswordField (contraseña oculta)
            pfPasswrd.setVisible(false);
            tfPasswrd.setVisible(true);  // Mostrar el TextField (contraseña visible)

            // Cambiar el icono al de "contraseña visible"
            ivEyeIcon.setImage(new Image("/resources/images/ShowPasswd.png"));

            // Actualizar el estado
            isPasswordVisible = true;
        } else {
            // Mostrar el PasswordField (contraseña oculta)
            tfPasswrd.setVisible(false);
            pfPasswrd.setVisible(true);  // Ocultar el TextField (contraseña visible)

            // Cambiar el icono al de "contraseña oculta"
            ivEyeIcon.setImage(new Image("/resources/images/HidePasswd.png"));

            // Actualizar el estado
            isPasswordVisible = false;
        }
    }

    private void handleWindowShowing(WindowEvent event) {
        logger.info("Beginning SingInController::handleWindowShowing");
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws WrongEmailFormatException {
        String email = this.emailText.getText().trim();
        String passwrd = this.pfPasswrd.getText().trim();

        try {
            if (email.equals("") || passwrd.equals("")) {
                Alert alert = new Alert(AlertType.ERROR, "Los campos usuario y contraseña deben estar rellenados", ButtonType.OK);
                // alert.getDialogPane().getStylesheets().add(getClass().getResource(""));
                alert.showAndWait();
            }
            WrongEmailFormatException.validateEmail(this.emailText.getText().trim());

            User user = new User(email, passwrd);
        } catch (WrongEmailFormatException e) {
            Logger.getLogger("userInterfaceTier.view").severe(e.getLocalizedMessage());
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).showAndWait();
        } catch (IllegalArgumentException e) {
            Logger.getLogger("userInterfaceTier.view").severe(e.getMessage());
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).showAndWait();
        }
    }
}
