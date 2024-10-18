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
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logicalModel.interfaces.Signable;
import logicalModel.model.User;
import uiExceptions.MaxCityCharacterException;
import uiExceptions.MaxStreetCharacterException;
import uiExceptions.PasswdsDontMatchException;
import uiExceptions.PatternEmailIncorrectException;
import uiExceptions.PatternFullNameIncorrectException;
import uiExceptions.PatternMobileIncorrectException;
import uiExceptions.PatternZipIncorrectException;
import uiExceptions.TextEmptyException;

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
    private Label labelErrorEmpty;

    /**
     * CheckBox for marking whether the user is active or not.
     */
    @FXML
    private CheckBox cbxStatus;

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

    @FXML
    private ToggleButton tgbEyeConfirmPasswd;

    @FXML
    private ToggleButton tgbEyePasswd;

    @FXML
    private ImageView imgEyePasswd;

    @FXML
    private ImageView imgEyeConfirmPasswd;

    @FXML
    private ContextMenu contextMenu;
    /**
     * Interface for the sign-up business logic.
     */
    private Signable signable;

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("SignUp");
        Image icon = new Image(getClass().getResourceAsStream("/resources/images/catrina.png"));
        stage.getIcons().add(icon);
        stage.setResizable(false);
        tfFullName.isFocused();
        btnSignUp.setDefaultButton(true);
        tfShowPassword.setVisible(false);
        tfShowConfirmPassword.setVisible(false);
        clearForm();
        clearErrorLabels();
        setTooltips();
        setPromptText();

        pfHiddenPassword.textProperty().addListener(this::passwrdIsVisible);
        tfShowPassword.textProperty().addListener(this::passwrdIsVisible);

        pfHiddenConfirmPassword.textProperty().addListener(this::passwrdIsVisible);
        tfShowConfirmPassword.textProperty().addListener(this::passwrdIsVisible);

        tgbEyePasswd.setOnAction(this::handelEyeIconToggleButtonAction);
        tgbEyeConfirmPasswd.setOnAction(this::handelEyeIconToggleButtonAction);

        btnSignUp.setOnAction(this::handleSignUp);
        hypSignUp.setOnAction(this::handleHyperLinkSignIn);
        signable = ClientFactory.getSignable();
        stage.showAndWait();

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

    private void setPromptText() {
        tfFullName.setPromptText("Enter your full name");
        tfEmail.setPromptText("Enter your email (ej.ejemplo@correo.com)");
        pfHiddenPassword.setPromptText("Enter your password");
        pfHiddenConfirmPassword.setPromptText("Repeat your password");
        tfShowPassword.setPromptText("Enter your password");
        tfShowConfirmPassword.setPromptText("Repeat your password");
        tfStreet.setPromptText("Enter your street");
        tfZip.setPromptText("Enter you ZIP (ej. 48013)");
        tfCity.setPromptText("Enter your city");
        tfMobile.setPromptText("Enter your mobile phone");

    }

    @FXML
    public void showContextMenu(MouseEvent event) {
        // Mostrar el menú contextual en la posición del mouse
        contextMenu.show(contextMenu.getScene().getWindow(), event.getScreenX(), event.getScreenY());
    }

    @FXML
    private void handleResetForm() {
        clearForm(); // Método que ya tienes para limpiar el formulario
        System.out.println("Formulario reseteado");
    }

    @FXML
    private void handleAnotherOption() {
        // Lógica para otra opción
        System.out.println("Otra opción seleccionada");
    }

    public void passwrdIsVisible(ObservableValue observable, String oldValue, String newValue) {

        if (pfHiddenPassword.isVisible()) {
            tfShowPassword.setText(pfHiddenPassword.getText());
        } else if (tfShowPassword.isVisible()) {
            pfHiddenPassword.setText(tfShowPassword.getText());
        }

        if (pfHiddenConfirmPassword.isVisible()) {
            tfShowConfirmPassword.setText(pfHiddenConfirmPassword.getText());

        } else if (tfShowConfirmPassword.isVisible()) {
            pfHiddenConfirmPassword.setText(tfShowConfirmPassword.getText());
        }

    }

    @FXML
    private void handelEyeIconToggleButtonAction(ActionEvent event) {

        Image ojoTachado = new Image(getClass().getResourceAsStream("/resources/images/HidePasswdOrange.png"));
        Image ojoNormal = new Image(getClass().getResourceAsStream("/resources/images/ShowPasswdOrange.png"));

        if (tgbEyePasswd.isSelected()) {
            pfHiddenPassword.setVisible(false);
            tfShowPassword.setVisible(true);
            imgEyePasswd.setImage(ojoTachado);
        } else {
            tfShowPassword.setVisible(false);
            pfHiddenPassword.setVisible(true);
            imgEyePasswd.setImage(ojoNormal);
        }

        if (tgbEyeConfirmPasswd.isSelected()) {
            pfHiddenConfirmPassword.setVisible(false);
            tfShowConfirmPassword.setVisible(true);
            imgEyeConfirmPasswd.setImage(ojoTachado);
        } else {
            tfShowConfirmPassword.setVisible(false);
            pfHiddenConfirmPassword.setVisible(true);
            imgEyeConfirmPasswd.setImage(ojoNormal);
        }
    }

    @FXML
    private void handleHyperLinkSignIn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("userInterfaceTier/view/SignInView.fxml"));
            Parent root = loader.load();
            SignInController controller = loader.getController();
            this.stage.close();

        } catch (IOException ex) {
            Logger.getLogger(SignInController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleSignUp(ActionEvent event) {
        clearErrorLabels(); // Limpiar mensajes de error

        User newUser = null;
        String name = null, street = null, city = null, password = null, email = null;
        int zip = 0, mobile = 0;
        boolean isValid = true;

        try {
            if (tfFullName.getText().isEmpty() || tfEmail.getText().isEmpty() || pfHiddenPassword.getText().isEmpty() || pfHiddenConfirmPassword.getText().isEmpty() || tfStreet.getText().isEmpty() || tfCity.getText().isEmpty() || tfZip.getText().isEmpty() || tfMobile.getText().isEmpty()) {
                throw new TextEmptyException("You must fill all the parameters");
            }

            try {
                if (!Pattern.matches("^[A-Za-zÀ-ÿ'\\s]+$", tfFullName.getText())) {
                    tfFullName.isFocused();
                    throw new PatternFullNameIncorrectException("The full name can't contain numbers");
                } else {
                    name = tfFullName.getText();
                }

            } catch (PatternFullNameIncorrectException e) {
                labelErrorFullName.setText(e.getMessage());
            }

            try {
                if ((!Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", tfEmail.getText())) || tfEmail.getText().length() > 320) {
                    tfEmail.isFocused();
                    throw new PatternEmailIncorrectException("The email must have a valid format");
                } else {
                    email = tfEmail.getText();
                }
            } catch (PatternEmailIncorrectException e) {
                labelErrorEmail.setText(e.getMessage());
            }

            try {
                if (!pfHiddenPassword.getText().equalsIgnoreCase(pfHiddenConfirmPassword.getText())) {
                    pfHiddenPassword.isFocused();
                    throw new PasswdsDontMatchException("The password doesnt match");
                } else {
                    password = pfHiddenPassword.getText();
                }
            } catch (PasswdsDontMatchException e) {
                labelErrorConfirmPasswd.setText(e.getMessage());
            }

            try {
                if (tfStreet.getText().length() > 255) {
                    tfStreet.isFocused();
                    throw new MaxStreetCharacterException("The street must be shorter");
                } else {
                    street = tfStreet.getText();
                }
            } catch (MaxStreetCharacterException e) {
                labelErrorStreet.setText(e.getMessage());
            }

            try {
                if (!Pattern.matches("\\d{5}$", tfZip.getText())) {
                    tfZip.isFocused();
                    throw new PatternZipIncorrectException("Zip code must have 5 numeric numbers");
                } else {
                    zip = Integer.parseInt(tfZip.getText());
                }
            } catch (PatternZipIncorrectException e) {
                labelErrorZip.setText(e.getMessage());
            }

            try {
                if (tfCity.getText().length() > 58) {
                    tfCity.isFocused();
                    throw new MaxCityCharacterException("The street must be shorter");
                } else {
                    city = tfCity.getText();
                }
            } catch (MaxCityCharacterException e) {
                labelErrorCity.setText(e.getMessage());
            }

            try {
                if (!Pattern.matches("\\d{9}$", tfMobile.getText())) {
                    tfMobile.isFocused();
                    throw new PatternMobileIncorrectException("Phone number must have 9 numeric numbers");
                } else {
                    mobile = Integer.parseInt(tfMobile.getText());
                }
            } catch (PatternMobileIncorrectException e) {
                labelErrorMobile.setText(e.getMessage());
            }

            boolean active = cbxStatus.selectedProperty().getValue();

            newUser = new User(email, password, name, street, mobile, city, zip, active);

            //User newUserValidate = ClientFactory.getSignable().signUp(newUser);
        } catch (TextEmptyException e) {
            labelErrorEmpty.setText(e.getMessage());
        }

    }

    private void clearErrorLabels() {
        labelErrorFullName.setText("");
        labelErrorEmail.setText("");
        labelErrorConfirmPasswd.setText("");
        labelErrorStreet.setText("");
        labelErrorZip.setText("");
        labelErrorCity.setText("");
        labelErrorMobile.setText("");
        labelErrorEmpty.setText("");
    }

    private void clearForm() {
        tfFullName.clear();
        tfEmail.clear();
        pfHiddenPassword.clear();
        pfHiddenConfirmPassword.clear();
        tfStreet.clear();
        tfZip.clear();
        tfCity.clear();
        tfMobile.clear();
        cbxStatus.setSelected(false);
    }

}
