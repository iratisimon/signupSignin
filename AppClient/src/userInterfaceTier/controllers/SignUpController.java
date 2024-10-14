/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterfaceTier.controllers;

import clientBusinessLogic.ClientFactory;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicalModel.interfaces.Signable;
import logicalModel.model.User;

/**
 *
 * @author 2dam
 */
public class SignUpController {
 
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField tfFullName;

    @FXML
    private Label labelFullName;

    @FXML
    private TextField tfEmail;

    @FXML
    private Label labelEmail;

    @FXML
    private TextField pfPassword;

    @FXML
    private Label labelPasswd;

    @FXML
    private TextField pfConfirmPassword;

    @FXML
    private Label labelRepeatPasswd;

    @FXML
    private TextField tfStreet;

    @FXML
    private Label labelStreet; 

    @FXML
    private TextField tfZip;

    @FXML
    private Label labelPostal;

    @FXML
    private TextField tfCity;

    @FXML
    private Label labelCity;

    @FXML
    private TextField tfMobile;

    @FXML
    private Label labelMobile;

    @FXML
    private CheckBox cbActive;

    @FXML
    private Button btnSignUp;

    @FXML
    private Hyperlink hpSignIn;
    
    @FXML
    private ImageView imgFullName;

    @FXML
    private ImageView imgEmail;

    @FXML
    private ImageView imgPassword;

    @FXML
    private ImageView imgRepeatPassword;

    @FXML
    private ImageView imgStreet;

    @FXML
    private ImageView imgZip;

    @FXML
    private ImageView imgCity;

    @FXML
    private ImageView imgMobile;
    
    private Stage stage;
    private Signable signable;
    
    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("SignUp");
        stage.setResizable(false);
        tfFullName.isFocused();
        tfFullName.setText("");
        tfEmail.setText("");
        pfPassword.setText("");
        tfStreet.setText("");
        tfZip.setText("");
        tfCity.setText("");
        tfMobile.setText("");
        pfConfirmPassword.setText("");
        cbActive.setSelected(false);
        btnSignUp.setDefaultButton(true);
        
        Tooltip.install(imgFullName, new Tooltip("Enter your full name"));
        Tooltip.install(imgEmail, new Tooltip("Enter your email address"));
        Tooltip.install(imgPassword, new Tooltip("Enter your password"));
        Tooltip.install(imgRepeatPassword, new Tooltip("Repeat the password"));
        Tooltip.install(imgStreet, new Tooltip("Enter your street address"));
        Tooltip.install(imgZip, new Tooltip("Enter your ZIP code"));
        Tooltip.install(imgCity, new Tooltip("Enter your city"));
        Tooltip.install(imgMobile, new Tooltip("Enter your phone number"));

        btnSignUp.setOnAction(this::handleSignUp);
        signable = ClientFactory.getSignable();
        stage.showAndWait();
        
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    
    @FXML
    private void handleSignUp(ActionEvent event) {

        String name = tfFullName.getText();
        String email = tfEmail.getText();
        String password = pfPassword.getText();
        String street = tfStreet.getText();
        String city = tfCity.getText();
        int mobile = Integer.parseInt(tfMobile.getText());
        int zip = Integer.parseInt(tfZip.getText());
        boolean active = cbActive.selectedProperty().getValue();

        //setMnemonicParsing
        //Tooltip
        // Crear una instancia de User con los datos proporcionados
        User newUser = new User(email, password, name, street, mobile, city, zip, active);
        
        User newUserValidate = ClientFactory.getSignable().signUp(newUser);
        // Aquí puedes manejar la lógica de registro, como guardar el usuario en una base de datos
        // Ejemplo: userService.register(newUser);
        //this.tfUsuario.getText().trim()

    }
    
    
}
