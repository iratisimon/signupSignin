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
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
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
import javafx.stage.WindowEvent;
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

    /**
     * AnchorPane for the layout of the SignUp view.
     */
    @FXML
    private AnchorPane anchorPane;

    /**
     * ContextMenu that provides options when right-clicking in the SignUp view.
     */
    @FXML
    private ContextMenu contextMenu;

    /**
     * TextField for entering the user's full name.
     */
    @FXML
    private TextField tfFullName;

    /**
     * ToggleButton for showing or hiding the password.
     */
    @FXML
    private ToggleButton tgbEyePasswd;

    /**
     * ToggleButton for showing or hiding the confirm password.
     */
    @FXML
    private ToggleButton tgbEyeConfirmPasswd;

    /**
     * TextField for entering the user's password.
     */
    @FXML
    private TextField tfShowPassword;

    /**
     * TextField for confirming the user's password.
     */
    @FXML
    private TextField tfShowConfirmPassword;

    /**
     * CheckBox for marking whether the user is active or not.
     */
    @FXML
    private CheckBox cbxStatus;

    /**
     * TextField for entering the user's email address.
     */
    @FXML
    private TextField tfEmail;

    /**
     * TextField for entering the user's street address.
     */
    @FXML
    private TextField tfStreet;

    /**
     * TextField for entering the user's ZIP code.
     */
    @FXML
    private TextField tfZip;

    /**
     * TextField for entering the user's city.
     */
    @FXML
    private TextField tfCity;

    /**
     * TextField for entering the user's mobile phone number.
     */
    @FXML
    private TextField tfMobile;

    /**
     * PasswordField for entering the user's password.
     */
    @FXML
    private PasswordField pfHiddenPassword;

    /**
     * PasswordField for confirming the user's password.
     */
    @FXML
    private PasswordField pfHiddenConfirmPassword;

    /**
     * Button to trigger the sign-up process.
     */
    @FXML
    private Button btnSignUp;

    /**
     * Label for displaying a general error message when the form is incomplete.
     */
    @FXML
    private Label labelErrorEmpty;

    /**
     * Label for displaying validation errors for the full name.
     */
    @FXML
    private Label labelErrorFullName;

    /**
     * Label for displaying validation errors for the email.
     */
    @FXML
    private Label labelErrorEmail;

    /**
     * Label for displaying validation errors for the password confirmation.
     */
    @FXML
    private Label labelErrorConfirmPasswd;

    /**
     * Label for displaying validation errors for the street address.
     */
    @FXML
    private Label labelErrorStreet;

    /**
     * Label for displaying validation errors for the ZIP code.
     */
    @FXML
    private Label labelErrorZip;

    /**
     * Label for displaying validation errors for the city.
     */
    @FXML
    private Label labelErrorCity;

    /**
     * Label for displaying validation errors for the mobile phone number.
     */
    @FXML
    private Label labelErrorMobile;

    /**
     * Hyperlink to navigate to the SignIn view.
     */
    @FXML
    private Hyperlink hypSignUp;

    /**
     * ImageView for toggling the visibility of the password field.
     */
    @FXML
    private ImageView imgEyePasswd;

    /**
     * ImageView for toggling the visibility of the confirm password field.
     */
    @FXML
    private ImageView imgEyeConfirmPasswd;

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

        //Establecer el titulo de la ventana al valor “SignUp”.
        stage.setTitle("SignUp");
        //Añadir a la ventana un icono de una catrina.
        Image icon = new Image(getClass().getResourceAsStream("/resources/images/catrina.png"));
        stage.getIcons().add(icon);
        //La ventana no debe ser redimensionable.
        stage.setResizable(false);
        //Ventana modal.
        stage.initModality(Modality.APPLICATION_MODAL);
        clearForm();
        //El foco se centra en el campo FullName (tfFullName).  
        tfFullName.isFocused();
        //El botón "btnSignUp" es el botón por defecto.
        btnSignUp.setDefaultButton(true);
        //El campo “tfShowPassword” estará oculto.
        tfShowPassword.setVisible(false);
        //El campo “tfShowConfirmPassword” estará oculto
        tfShowConfirmPassword.setVisible(false);
        //Los labelError estarán ocultos.MODIFICAR EL METODO SI DA TIEMPO
        clearErrorLabels();
        setTooltips();
        setPromptText();
        //Crear un ContextMenu con las opciones: Reset Form, Help, y About App.
        setUpContextMenu();

        //Handle para que el texto cambio de texto en pfHiddenPassword y tfShowPassword.
        pfHiddenPassword.textProperty().addListener(this::passwrdIsVisible);
        tfShowPassword.textProperty().addListener(this::passwrdIsVisible);

        //Handle para que el texto cambio de texto en pfHiddenConfirmPassword" y tfShowConfirmPassword.
        pfHiddenConfirmPassword.textProperty().addListener(this::passwrdIsVisible);
        tfShowConfirmPassword.textProperty().addListener(this::passwrdIsVisible);

        //Handle para manejar el tgbEyePasswd y tbgEyeConfirmPasswd
        tgbEyePasswd.setOnAction(this::handelEyeIconToggleButtonAction);
        tgbEyeConfirmPasswd.setOnAction(this::handelEyeIconToggleButtonAction);

        //Handle para manejar el boton btnSignUp
        btnSignUp.setOnAction(this::handleSignUp);
        //Haddle para manejar el hiperlink hypSigIn
        hypSignUp.setOnAction(this::handleHyperLinkSignIn);
        //Haddle para manejar el cierre de la ventana
        stage.setOnCloseRequest(this::handleCloseRequest);
        signable = ClientFactory.getSignable();
        stage.showAndWait();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void passwrdIsVisible(ObservableValue observable, String oldValue, String newValue) {

        //El texto del "pfHiddenPassword" se copiará en “tfShowPassword” y viceversa.
        if (pfHiddenPassword.isVisible()) {
            tfShowPassword.setText(pfHiddenPassword.getText());
        } else if (tfShowPassword.isVisible()) {
            pfHiddenPassword.setText(tfShowPassword.getText());
        }

        //El texto del "pfHiddenConfirmPassword" se copiará en “tfShowConfirmPassword” y viceversa.
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
            //PasswordField “pfHiddenPassword” se volverá invisible
            pfHiddenPassword.setVisible(false);
            //TextField “tfShowPassword” se volverá visible
            tfShowPassword.setVisible(true);
            //La imgimgEyePasswd cambia a HidePasswdOrange.png
            imgEyePasswd.setImage(ojoTachado);
        } else {
            //TextField “tfShowPassword” se volverá invisible
            tfShowPassword.setVisible(false);
            //PasswordField “pfHiddenPassword” se volverá visible
            pfHiddenPassword.setVisible(true);
            //La imgEyePasswd cambia a ShowPasswdOrange.png
            imgEyePasswd.setImage(ojoNormal);
        }

        if (tgbEyeConfirmPasswd.isSelected()) {
            //PasswordField pfHiddenConfirmPassword se volverá invisible
            pfHiddenConfirmPassword.setVisible(false);
            //TextField “tfShowConfirmPassword” se volverá visible
            tfShowConfirmPassword.setVisible(true);
            //La imgEyeConfirmPasswd cambia a HidePasswdOrange.png
            imgEyeConfirmPasswd.setImage(ojoTachado);
        } else {
            //TextField “tfShowConfirmPassword” se volverá invisible
            tfShowConfirmPassword.setVisible(false);
            //PasswordField pfHiddenConfirmPassword se volverá visible
            pfHiddenConfirmPassword.setVisible(true);
            //La imgEyeConfirmPasswd cambia a ShowPasswdOrange.png
            imgEyeConfirmPasswd.setImage(ojoNormal);
        }
    }

    @FXML
    private void handleSignUp(ActionEvent event) {
        // Limpiar mensajes de error cada que se de al boton btnSignUp
        clearErrorLabels();
        //Factoria
        Signable signable;
        User newUser;
        User newUserValidate;
        //Variables para crear un usuario
        String name = null, street = null, city = null, password = null, email = null;
        int zip = 0, mobile = 0;
        boolean active;

        try {
            //Validar que todos los campos estén diligenciados
            TextEmptyException.checkFields(tfFullName, tfEmail, pfHiddenPassword, pfHiddenConfirmPassword,
                    tfStreet, tfCity, tfZip, tfMobile);
            try {
                //Validar que el campo "tfFullName" no contenga números.
                PatternFullNameIncorrectException.validateFullName(tfFullName);
                name = tfFullName.getText();
            } catch (PatternFullNameIncorrectException e) {
                //Si contiene números mostrar en “labelErrorFullName”, exeption “PatternFullNameIncorrectException”.
                labelErrorFullName.setText(e.getMessage());
            }
            try {
                //Validar que en el campo "tfEmail" se cumpla con el formato correcto del email introducido y con un maximo de 320 caracteres.
                PatternEmailIncorrectException.validateEmail(tfEmail);
                email = tfEmail.getText();
            } catch (PatternEmailIncorrectException e) {
                //Sino, mostrar en “labelErrorEmail”, exeption “PatternEmailncorrectException”.
                labelErrorEmail.setText(e.getMessage());
            }
            try {
                //Validar que el contenido de “pfHiddenConfirmPassword” sea igual al contenido almacenado en “pfHiddenPassword”.
                PasswdsDontMatchException.validatePasswords(pfHiddenPassword, pfHiddenConfirmPassword);
                password = pfHiddenPassword.getText();
            } catch (PasswdsDontMatchException e) {
                //Si no cumple mostrar en “labelErrorConfirmPasswd”, exeption “PasswdsDontMatchException”
                labelErrorConfirmPasswd.setText(e.getMessage());
            }
            try {
                //Validar que "pfStreet" no tenga mas de 255 carácteres.
                MaxStreetCharacterException.validateStreetLength(tfStreet);
                street = tfStreet.getText();
            } catch (MaxStreetCharacterException e) {
                //Sino, mostrar en “labelErrorStreet”, exception “MaxStreetCharacterException”.
                labelErrorStreet.setText(e.getMessage());
            }
            try {
                //Validar que "tfZip" no tenga letras y que tenga un máximo de 5 números.
                PatternZipIncorrectException.validateZipFormat(tfZip);
                zip = Integer.parseInt(tfZip.getText());
            } catch (PatternZipIncorrectException e) {
                //Sino, mostrar en “labelErrorZip”, exception “PatternZipIncorrectException”.
                labelErrorZip.setText(e.getMessage());
            }

            try {
                //Validar que "tfCity" tena como máximo 58 caracteres. 
                MaxCityCharacterException.validateCityLength(tfCity);
                city = tfCity.getText();
            } catch (MaxCityCharacterException e) {
                //Si excede mostrar en “labelErrorCity” exception “MaxCityCharacterException”.
                labelErrorCity.setText(e.getMessage());
            }

            try {
                //Validar que "tfMobile" tenga solo números y un máximo de 9 caracteres.
                PatternMobileIncorrectException.validateMobileFormat(tfMobile);
                mobile = Integer.parseInt(tfMobile.getText());
            } catch (PatternMobileIncorrectException e) {
                //Sino mostrar en “labelErrorMobile” exception “PatternMobileIncorrectException”.
                labelErrorMobile.setText(e.getMessage());
            }
            //Si se selecciona, el usuario se considerará activo. 
            // Si no se selecciona, el usuario se considerará inactivo.
            active = cbxStatus.selectedProperty().getValue();
            /*Una vez que todas las validaciones están realizadas, 
             *carga los datos de los campos en un objeto User.
             */
            newUser = new User(email, password, name, street, mobile, city, zip, active);
            /*Se llama a la factoría “ClientFactory” para conseguir una 
             *implementación de la interfaz “Signable”  
             */
            signable = ClientFactory.getSignable();
            try {
                //y se llama al método signUp  pasándole el objeto User.
                newUserValidate = signable.signUp(newUser);
                if (newUserValidate != null) {
                    //Mostrar un Alert de tipo INFORMATION con un mensaje "Registro exitoso". 
                    new Alert(Alert.AlertType.CONFIRMATION, "You have successfully registered.", ButtonType.OK).showAndWait();
                    //Después de aceptar el mensaje, se cierra la ventana 
                    //de SignUp y se devuelve el control a la ventana SignIn.
                    stage.close();    
                }
            } catch (Exception e) {
                //metenr los errores que nos mande el servidor, que aun no los tengo.
            }

        } catch (TextEmptyException e) {
            //Si todos los campos no están diligenciados lazar la  exception “TextEmptyException” en “labelErrorEmpty”.
            labelErrorEmpty.setText(e.getMessage());
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

    private void setTooltips() {
        Tooltip tooltipFN = new Tooltip("Enter your full name");
        Tooltip.install(tfFullName, tooltipFN);
        Tooltip tooltipE = new Tooltip("Enter your email address");
        Tooltip.install(tfEmail, tooltipE);
        Tooltip tooltipP = new Tooltip("Enter your password");
        Tooltip.install(tfShowPassword, tooltipP);
        Tooltip tooltipRP = new Tooltip("Repeat the password");
        Tooltip.install(tfShowConfirmPassword, tooltipRP);
        Tooltip tooltipS = new Tooltip("Enter your street address");
        Tooltip.install(tfStreet, tooltipS);
        Tooltip tooltipZ = new Tooltip("Enter your ZIP code");
        Tooltip.install(tfCity, tooltipZ);
        Tooltip tooltipC = new Tooltip("Enter your city");
        Tooltip.install(tfCity, tooltipC);
        Tooltip tooltipM = new Tooltip("Enter your phone number");
        Tooltip.install(tfMobile, tooltipM);
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

    private void setUpContextMenu() {
        contextMenu = new ContextMenu();

        //Crear los elementos del menu
        MenuItem resetFormItem = new MenuItem("Reset Form");
        MenuItem helpItem = new MenuItem("Help");
        MenuItem aboutAppItem = new MenuItem("About App");
        contextMenu.getItems().addAll(resetFormItem, helpItem, aboutAppItem);

        //Handdle de las opciones del menu
        resetFormItem.setOnAction(this::handleResetForm);
        helpItem.setOnAction(this::handleHelp);
        aboutAppItem.setOnAction(this::handleAboutApp);
        //Asociar el Context Menu a toda la ventana
        anchorPane.setOnMouseClicked(this::showContextMenu);
    }

    private void showContextMenu(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY) {
            contextMenu.show(anchorPane, event.getScreenX(), event.getScreenY());
        } else {
            contextMenu.hide();
        }
    }

    private void handleResetForm(ActionEvent event) {
        //Todos los campos del formulario se restablecen a sus valores predeterminados (vacíos).
        clearForm();
        clearErrorLabels();
    }

    private void handleHelp(ActionEvent event) {
        //Se muestra un cuadro de diálogo (Alert) con errores comunes y recomendaciones de seguridad para crear la cuenta. 
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Security Recommendations for Your Password");
        alert.setHeaderText("Security Recommendations");
        alert.setContentText("- Use a combination of uppercase and lowercase letters, numbers, and symbols.\n"
                + "- Avoid using personal information, such as names or birth dates.\n"
                + "- Change your password regularly to keep your account secure."
        );
        alert.showAndWait();
    }

    private void handleAboutApp(ActionEvent event) {
        //Se muestra un cuadro de diálogo (Alert) con información sobre la aplicación: nombre, versión, autoras, y propósito de la app.

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About the Application");
        alert.setHeaderText("SignUp Window Information");
        alert.setContentText(
                "Name: SignUp\n"
                + "Version: 1.0\n"
                + "Authors: Elbire Haro, Meylin Gutierrez\n"
                + "Purpose: This window helps the user create an account in the database."
        );

        // Mostrar el cuadro de diálogo y esperar hasta que se cierre
        alert.showAndWait();
    }

    private void handleCloseRequest(WindowEvent event) {
        event.consume();
        showExitConfirmation();
    }

    private void showExitConfirmation() {
        //Mostrar un Alert de tipo CONFIRMATION con el mensaje: "¿Estás seguro de que deseas salir?". 
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("¿Estás seguro de que deseas salir?");
        alert.showAndWait();
        ButtonType response = alert.getResult();
        handleCloseConfirmation(response);
    }

    public void handleCloseConfirmation(ButtonType response) {
        if (response == ButtonType.OK) {
            //  Si el usuario confirma, cerrar la ventana y abrir la ventana Sign In. 
            //  Sino confirma, mantener la ventana abierta.
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("userInterfaceTier/view/SignInView.fxml"));
                Parent root = loader.load();
                SignInController controller = loader.getController();
                stage.close();
                Stage signInStage = new Stage();
                signInStage.setScene(new Scene(root));
                signInStage.setTitle("Sign In");
                signInStage.show();
            } catch (IOException ex) {
                Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
