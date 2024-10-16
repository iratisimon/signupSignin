/**
 * Controller class for the SignUp view in the user interface tier.
 * It handles user interactions for the sign-up process, validates input fields,
 * and registers new users with the system. It also includes navigation
 * to the SignIn view.
 *
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
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
 * @author Meylin
 * @author Elbire
 */
public class SignUpController {

    @FXML
    private AnchorPane anchorPane;
    /**
     * TextField for entering the user's full name.
     */
    @FXML
    private TextField tfFullName;

    /**
     * Label for displaying validation errors for the full name.
     */
    @FXML
    private Label labelErrorFullName;

    /**
     * TextField for entering the user's email address.
     */
    @FXML
    private TextField tfEmail;

    /**
     * Label for displaying validation errors for the email.
     */
    @FXML
    private Label labelErrorEmail;

    /**
     * PasswordField for entering the user's password.
     */
    @FXML
    private PasswordField pfHiddenPassword;

    /**
     * TextField for entering the user's password.
     */
    @FXML
    private TextField tfShowPassword;

    /**
     * PasswordField for confirming the user's password.
     */
    @FXML
    private PasswordField pfHiddenConfirmPassword;

    /**
     * TextField for confirming the user's password.
     */
    @FXML
    private TextField tfShowConfirmPassword;

    /**
     * Label for displaying validation errors for the password confirmation.
     */
    @FXML
    private Label labelErrorConfirmPasswd;

    /**
     * TextField for entering the user's street address.
     */
    @FXML
    private TextField tfStreet;

    /**
     * Label for displaying validation errors for the street address.
     */
    @FXML
    private Label labelErrorStreet;

    /**
     * TextField for entering the user's ZIP code.
     */
    @FXML
    private TextField tfZip;

    /**
     * Label for displaying validation errors for the ZIP code.
     */
    @FXML
    private Label labelErrorZip;

    /**
     * TextField for entering the user's city.
     */
    @FXML
    private TextField tfCity;

    /**
     * Label for displaying validation errors for the city.
     */
    @FXML
    private Label labelErrorCity;

    /**
     * TextField for entering the user's mobile phone number.
     */
    @FXML
    private TextField tfMobile;

    /**
     * Label for displaying validation errors for the mobile phone number.
     */
    @FXML
    private Label labelErrorMobile;

    /**
     * Label for displaying a general error message when the form is incomplete.
     */
    @FXML
    private Label labelErrorPFEmpty;

    /**
     * CheckBox for marking whether the user is active or not.
     */
    @FXML
    private CheckBox cbActive;

    /**
     * Button to trigger the sign-up process.
     */
    @FXML
    private Button btnSignUp;

    /**
     * Hyperlink to navigate to the SignIn view.
     */
    @FXML
    private Hyperlink hypSignUp;

    /**
     * ImageView to show a tooltip for full name input.
     */
    @FXML
    private ImageView imgFullName;

    /**
     * ImageView to show a tooltip for email input.
     */
    @FXML
    private ImageView imgEmail;

    /**
     * ImageView to show a tooltip for password input.
     */
    @FXML
    private ImageView imgPassword;

    /**
     * ImageView to show a tooltip for password confirmation input.
     */
    @FXML
    private ImageView imgRepeatPassword;

    /**
     * ImageView to show a tooltip for street address input.
     */
    @FXML
    private ImageView imgStreet;

    /**
     * ImageView to show a tooltip for ZIP code input.
     */
    @FXML
    private ImageView imgZip;

    /**
     * ImageView to show a tooltip for city input.
     */
    @FXML
    private ImageView imgCity;

    /**
     * ImageView to show a tooltip for mobile phone input.
     */
    @FXML
    private ImageView imgMobile;

    /**
     * The stage for displaying the SignUp view.
     */
    private Stage stage;

    /**
     * Interface for the sign-up business logic.
     */
    private Signable signable;

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("SignUp");
        Image icon = new Image(getClass().getResourceAsStream("/resources/images/catrina.png"));
        stage.getIcons().add(icon);
        stage.setResizable(false);
        tfFullName.isFocused();
        //clearForm();
        btnSignUp.setDefaultButton(true);
        setTooltips();
        btnSignUp.setOnAction(this::handleSignUp);
        hypSignUp.setOnAction(this::handleHyperLinkSignIn);
        signable = ClientFactory.getSignable();
        stage.show();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void setTooltips() {
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
    }

    @FXML
    private void handleHyperLinkSignIn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("userInterfaceTier/view/SignInView.fxml"));
            Parent root = loader.load();
            SignInController controller = loader.getController();
            Stage stage = new Stage();
            controller.setStage(stage);
            controller.initStage(root);
        } catch (IOException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleSignUp(ActionEvent event) {
        clearErrorLabels(); // Limpiar mensajes de error

        User newUser = null;
        String name, street, city, password = null, email = null;
        int zip = 0, mobile = 0;
        boolean isValid = true;

        /*isValid &= validateEmptyField(tfFullName, labelErrorFullName, "Full name is required");
        isValid &= validateEmptyField(tfEmail, labelErrorEmail, "Email is required");
        isValid &= validateEmptyField(pfHiddenConfirmPassword, labelErrorConfirmPasswd, "Confirm password is required");
        isValid &= validateEmptyField(tfStreet, labelErrorStreet, "Street is required");
        isValid &= validateEmptyField(tfCity, labelErrorCity, "City is required");
        isValid &= validateEmptyField(tfZip, labelErrorZip, "ZIP code is required");
        isValid &= validateEmptyField(tfMobile, labelErrorMobile, "Mobile number is required");
         */
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
                if (!pfHiddenPassword.getText().equalsIgnoreCase(pfHiddenConfirmPassword.getText())) {
                    throw new InvalidConfirmPassword("The password doesnt match");
                } else {
                    password = pfHiddenPassword.getText();
                }
            } catch (InvalidConfirmPassword e) {
                labelErrorConfirmPasswd.setText(e.getMessage());
            }

            try {
                if (!Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", tfEmail.getText())) {
                    throw new InvalidEmailException("The email must have a valid format");
                } else {
                    email = tfEmail.getText();
                }
            } catch (InvalidEmailException e) {
                labelErrorEmail.setText(e.getMessage());
            }
            try {
                if (!Pattern.matches("\\d{9}$", tfMobile.getText())) {
                    throw new InvalidMobileException("Phone number must have 9 numeric numbers");
                } else {
                    mobile = Integer.parseInt(tfMobile.getText());
                }
            } catch (InvalidMobileException e) {
                labelErrorMobile.setText(e.getMessage());
            }
            try {
                if (!Pattern.matches("\\d{5}$", tfZip.getText())) {
                    throw new InvalidZipException("Zip code must have 5 numeric numbers");
                } else {
                    zip = Integer.parseInt(tfZip.getText());
                }
            } catch (InvalidZipException e) {
                labelErrorZip.setText(e.getMessage());
            }
            newUser = new User(email, password, name, street, mobile, city, zip, active);

            //User newUserValidate = ClientFactory.getSignable().signUp(newUser);
        } catch (TextFileEmptyException e) {
            labelErrorPFEmpty.setText(e.getMessage());
        }

    }

    private boolean validateEmptyField(TextField textField, Label label, String errorMessage) {
        if (textField.getText().isEmpty()) {
            label.setText(errorMessage);
            return false;
        }
        return true;
    }

    private void clearErrorLabels() {
        labelErrorFullName.setText("");
        labelErrorEmail.setText("");
        labelErrorConfirmPasswd.setText("");
        labelErrorStreet.setText("");
        labelErrorZip.setText("");
        labelErrorCity.setText("");
        labelErrorMobile.setText("");
        labelErrorPFEmpty.setText("");
    }

    /*private void clearForm() {
        tfFullName.clear();
        tfEmail.clear();
        pfHiddenPassword.clear();
        pfHiddenConfirmPassword.clear();
        tfStreet.clear();
        tfZip.clear();
        tfCity.clear();
        tfMobile.clear();
        cbActive.setSelected(false);
        labelErrorFullName.setText("");
        labelErrorEmail.setText("");
        labelErrorConfirmPasswd.setText("");
        labelErrorStreet.setText("");
        labelErrorZip.setText("");
        labelErrorCity.setText("");
        labelErrorMobile.setText("");
        labelErrorPFEmpty.setText("");
    }*/

}
