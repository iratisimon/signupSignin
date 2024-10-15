/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterfaceTier.controllers;

import clientBusinessLogic.ClientFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import uiExceptions.InvalidZipException;

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
    private Label labelZip;

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
        
        Tooltip tooltipFN = new Tooltip("Enter your full name");
        Tooltip.install(imgFullName, tooltipFN);
        Tooltip tooltipE = new Tooltip("Enter your email address");
        Tooltip.install(imgEmail, tooltipE);
        Tooltip tooltipP = new Tooltip("Enter your password");
        Tooltip.install(imgPassword, tooltipP);
        Tooltip tooltipRP = new Tooltip("Repeat the password");
        Tooltip.install(imgRepeatPassword, tooltipRP);
        Tooltip tooltipS = new Tooltip("Enter your street address");
        Tooltip.install(imgStreet, tooltipS);
        Tooltip tooltipZ = new Tooltip("Enter your ZIP code");
        Tooltip.install(imgZip, tooltipZ);
        Tooltip tooltipC = new Tooltip("Enter your city");
        Tooltip.install(imgCity, tooltipC);
        Tooltip tooltipM = new Tooltip("Enter your phone number");
        Tooltip.install(imgMobile, tooltipM);

        btnSignUp.setOnAction(this::handleSignUp);
        hpSignIn.setOnAction(this::handleHyperLinkSignIn);
        signable = ClientFactory.getSignable();
        stage.showAndWait();
        
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleHyperLinkSignIn(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("userInterfaceTier/view/SignInView.fxml"));
            Parent root = (Parent) loader.load();
            SignInController controller = (SignInController) loader.getController();
            Stage modalStage = new Stage();
            //controller.setStage(modalStage);
            //controller.initStage(root);
        } catch (IOException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void handleSignUp(ActionEvent event) {

        String name = tfFullName.getText();
        String email = tfEmail.getText();
        String password = pfPassword.getText();
        String street = tfStreet.getText();
        String city = tfCity.getText();
        //int mobile = Integer.parseInt(tfMobile.getText());
        //int zip = Integer.parseInt(tfZip.getText());
        //boolean active = cbActive.selectedProperty().getValue();

        //setMnemonicParsing

        try{  
            if(!Pattern.matches("\\d{5}$", tfZip.getText())){
                throw new InvalidZipException("Zip code must have 5 numeric numbers");
            }
        }catch(InvalidZipException e){
            labelZip.setText(e.getMessage());
        }
        //User newUser = new User(email, password, name, street, mobile, city, zip, active);
        
        //User newUserValidate = ClientFactory.getSignable().signUp(newUser);


    }
    
    
}
