/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterfaceTier.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import logicalModel.model.User;
import uiExceptions.WrongEmailFormatException;

/**
 *
 * @author 2dam
 */
public class SignInController implements Initializable {

    @FXML
    private TextField emailText;
    @FXML
    private PasswordField passwrdText;
    @FXML
    private Button acceptButton;
    @FXML
    private Label errorLabel;
    @FXML
    private ToggleButton passwrdButton;
    
    private boolean isPasswordVisible = false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializa el botón de aceptación como deshabilitado
        acceptButton.setDisable(true);

      
    }


    @FXML
    private void handleButtonAction(ActionEvent event) {

        String email = emailText.getText().trim();
        String password = passwrdText.getText().trim();

        try {
            // Validar formato del email usando la excepción
            WrongEmailFormatException.validateEmail(email);

            // Aquí llamas a la fábrica para obtener la implementación de Signable
            User user = new User(email, password);
            
           

   
        } catch (WrongEmailFormatException e) {
           Logger.getLogger("userInterfaceTier.view").severe(e.getLocalizedMessage());
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).showAndWait();
        }

    }
}
