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
import uiExceptions.InvalidConfirmPassword;
import uiExceptions.InvalidEmailException;
import uiExceptions.InvalidMobileException;
import uiExceptions.InvalidZipException;
import uiExceptions.TextFileEmptyException;

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
    private Label labelEmpty;

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
        clearForm();
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
    private void handleHyperLinkSignIn(ActionEvent event) {
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
        //setMnemonicParsing
        User user = null;
        String name, street, city, password = null, email = null;
        int zip = 0, mobile = 0;
        boolean isValid = true;

        isValid &= validateEmptyField(tfFullName, labelFullName, "Full name is required");
        isValid &= validateEmptyField(tfEmail, labelEmail, "Email is required");
        isValid &= validateEmptyField(pfPassword, labelPasswd, "Password is required");
        isValid &= validateEmptyField(pfConfirmPassword, labelRepeatPasswd, "Confirm password is required");
        isValid &= validateEmptyField(tfStreet, labelStreet, "Street is required");
        isValid &= validateEmptyField(tfCity, labelCity, "City is required");
        isValid &= validateEmptyField(tfZip, labelZip, "ZIP code is required");
        isValid &= validateEmptyField(tfMobile, labelMobile, "Mobile number is required");

        try {
            if (!isValid) {
                throw new TextFileEmptyException("You must fill all the parameters");
            }
            name = tfFullName.getText();
            street = tfStreet.getText();
            city = tfCity.getText();
            //
            boolean active = cbActive.selectedProperty().getValue();
            try {
                if (!pfPassword.getText().equalsIgnoreCase(pfConfirmPassword.getText())) {
                    throw new InvalidConfirmPassword("The password doesnt match");
                } else {
                    password = pfPassword.getText();
                }
            } catch (InvalidConfirmPassword e) {
                labelRepeatPasswd.setText(e.getMessage());
            }

            try {
                if (!Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", tfEmail.getText())) {
                    throw new InvalidEmailException("The email must have a valid format");
                } else {
                    email = tfEmail.getText();
                }
            } catch (InvalidEmailException e) {
                labelEmail.setText(e.getMessage());
            }
            try {
                if (!Pattern.matches("\\d{9}$", tfMobile.getText())) {
                    throw new InvalidMobileException("Phone number must have 9 numeric numbers");
                } else {
                    mobile = Integer.parseInt(tfMobile.getText());
                }
            } catch (InvalidMobileException e) {
                labelMobile.setText(e.getMessage());
            }
            try {
                if (!Pattern.matches("\\d{5}$", tfZip.getText())) {
                    throw new InvalidZipException("Zip code must have 5 numeric numbers");
                } else {
                    zip = Integer.parseInt(tfZip.getText());
                }
            } catch (InvalidZipException e) {
                labelZip.setText(e.getMessage());
            }
            User newUser = new User(email, password, name, street, mobile, city, zip, active);

            //User newUserValidate = ClientFactory.getSignable().signUp(newUser);
        } catch (TextFileEmptyException e) {
            labelEmpty.setText(e.getMessage());
        }

    }

    private boolean validateEmptyField(TextField textField, Label label, String errorMessage) {
        if (textField.getText().isEmpty()) {
            label.setText(errorMessage);
            return false;
        }
        return true;
    }
    
    private void clearForm() {
        tfFullName.clear();
        tfEmail.clear();
        pfPassword.clear();
        pfConfirmPassword.clear();
        tfStreet.clear();
        tfZip.clear();
        tfCity.clear();
        tfMobile.clear();
        cbActive.setSelected(false);
        labelFullName.setText("");
        labelEmail.setText("");
        labelPasswd.setText("");
        labelRepeatPasswd.setText("");
        labelStreet.setText("");
        labelZip.setText("");
        labelCity.setText("");
        labelMobile.setText("");
        labelEmpty.setText("");
    }

}
