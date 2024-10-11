/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterfaceTier.controllers;

import clientBusinessLogic.Client;
import clientBusinessLogic.ClientFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logicalModel.model.User;

/**
 *
 * @author 2dam
 */
public class SignUpController {
 
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField mobileField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField zipField;
    @FXML
    private Button signUpButton;
    @FXML
    private Label statusLabel;

    
    @FXML
    private void handleSignUp() {
        String email = emailField.getText();
        String password = passwordField.getText();
        String name = nameField.getText();
        String street = streetField.getText();
        String city = cityField.getText();
        boolean active = true; // Asumiendo que el usuario es activo por defecto
        
        // Validar y parsear el campo de móvil
        int mobile;
        try {
            mobile = Integer.parseInt(mobileField.getText());
        } catch (NumberFormatException e) {
            statusLabel.setText("Número de móvil inválido.");
            return;
        }

        // Validar y parsear el campo de código postal
        int zip;
        try {
            zip = Integer.parseInt(zipField.getText());
        } catch (NumberFormatException e) {
            statusLabel.setText("Código postal inválido.");
            return;
        }

        // Crear una instancia de User con los datos proporcionados
        User newUser = new User(email, password, name, street, mobile, city, zip, active);
        
        User newUserValidate = ClientFactory.getSignable().signUp(newUser);
        // Aquí puedes manejar la lógica de registro, como guardar el usuario en una base de datos
        // Ejemplo: userService.register(newUser);

        // Mostrar mensaje de éxito
        statusLabel.setText("Registro exitoso para " + newUser.getName());
    }
    
    
}
