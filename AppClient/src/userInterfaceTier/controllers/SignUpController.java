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

    /**
     * Initializes and configures the registration window (SignUp).
     *
     * @param root The root node to be used for creating the scene of the
     * window.
     *
     * @authors Meylin, Elbire
     */
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

    /**
     * Sets the stage for the current window.
     *
     * @param stage The stage to be set for this window.
     *
     * This method assigns the provided stage to the instance variable, allowing
     * further configuration and manipulation of the window as needed.
     *
     * @author Elbire
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Synchronizes the text between the password fields and their visible
     * counterparts.
     *
     * This method updates the text of the hidden password fields based on the
     * visibility of the corresponding visible password fields and vice versa.
     *
     * @param observable The observable value that has changed (not used in this
     * method).
     * @param oldValue The old value of the observable (not used in this
     * method).
     * @param newValue The new value of the observable (not used in this
     * method).
     *
     * @authors Meylin, Elbire
     */
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

    /**
     * Handles the toggle action for the eye icon buttons to show or hide the
     * password fields.
     *
     * This method updates the visibility of the password fields and the
     * corresponding images based on whether the toggle buttons for showing or
     * hiding the password are selected.
     *
     * @param event The action event triggered by the toggle button.
     *
     * @authors Meylin, Elbire
     */
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

    /**
     * Handles the sign-up action when the user clicks the "Sign Up" button.
     *
     * @param event The action event triggered by the button click.
     *
     * @authors Elbire, Meylin
     */
    @FXML
    private void handleSignUp(ActionEvent event) {
        // Limpiar mensajes de error cada que se de al boton btnSignUp
        clearErrorLabels();
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

    /**
     * Handles the action when the user clicks on the hyperlink to sign in.
     *
     * This method attempts to load the SignIn view and close the current stage.
     * If an error occurs during the loading process, it logs the exception.
     *
     * @param event The action event triggered by the hyperlink click.
     *
     * @author Elbire
     */
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

    /**
     * Sets tooltips for the input fields in the SignUp form.
     *
     * Each tooltip provides a hint to the user about what to enter in the
     * corresponding field.</p>
     *
     * @author Elbire
     */
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

    /**
     * Sets the prompt text for the input fields in the SignUp form.
     *
     * This method configures the prompt text that appears in each input field,
     * providing users with a hint about the expected input.
     *
     * @author Meylin
     */
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

    /**
     * Clears the error messages displayed in the SignUp form.
     *
     * This method resets the text of all error label components to an empty
     * string, effectively removing any error messages that may have been
     * displayed to the user.
     *
     * @author Elbire
     */
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

    /**
     * Clears all input fields in the SignUp form.
     *
     * This method resets the values of all form fields to their default state.
     * Specifically, it clears the text from the following input fields and sets
     * the status checkbox to unchecked
     *
     * @author Elbire
     */
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

    /**
     * Sets up the context menu for the SignUp form.
     *
     * This method creates a context menu that provides users with options to
     * reset the form, access help, and view information about the application.
     *
     * Each menu item has an associated action handler that executes when the
     * item is selected. The context menu is displayed when the user
     * right-clicks anywhere in the SignUp window.
     *
     * @authors Meylin, Elbire
     */
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

    /**
     * Displays the context menu when the user right-clicks on the SignUp form.
     *
     * This method checks if the right mouse button (secondary button) was
     * clicked. If so, it shows the context menu at the position of the mouse
     * cursor. If any other mouse button is clicked, the context menu is hidden.
     *
     * @param event The mouse event that triggered this method.
     * @authors Meylin, Elbire
     */
    private void showContextMenu(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY) {
            contextMenu.show(anchorPane, event.getScreenX(), event.getScreenY());
        } else {
            contextMenu.hide();
        }
    }

    /**
     * Resets the SignUp form to its default values.
     *
     * This method clears all input fields in the form, setting them back to
     * their default (empty) state. It also clears any error messages that may
     * be displayed to the user.
     *
     * @param event The action event that triggered this method.
     * @authors Meylin, Elbire
     */
    private void handleResetForm(ActionEvent event) {
        //Todos los campos del formulario se restablecen a sus valores predeterminados (vacíos).
        clearForm();
        clearErrorLabels();
    }

    /**
     * Displays a dialog with common errors and security recommendations for
     * creating an account.
     *
     * This method shows an information alert dialog that provides users with
     * tips on creating a secure password.
     *
     * @param event The action event that triggered this method.
     * @authors Meylin, Elbire
     */
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

    /**
     * Displays a dialog with information about the application, including its
     * name, version, authors, and purpose.
     *
     * This method shows an information alert dialog that provides users with
     * details about the application.
     *
     * @param event The action event that triggered this method.
     * @authors Meylin, Elbire
     */
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

    /**
     * Handles the window close request event by showing a confirmation dialog
     * before closing the application.
     *
     * This method consumes the close request event and invokes a confirmation
     * dialog to ask the user if they really want to exit the application
     *
     * @param event The window event that triggered this method.
     * @authors Meylin
     */
    private void handleCloseRequest(WindowEvent event) {
        event.consume();
        showExitConfirmation();
    }

    /**
     * Displays a confirmation dialog asking the user if they are sure they want
     * to exit the application.
     *
     * @authors Meylin
     */
    private void showExitConfirmation() {
        //Mostrar un Alert de tipo CONFIRMATION con el mensaje: "¿Estás seguro de que deseas salir?". 
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.showAndWait();
        ButtonType response = alert.getResult();
        handleCloseConfirmation(response);
    }

    /**
     * Handles the confirmation response when the user attempts to close the
     * application.
     *
     *
     * @param response The user's response to the exit confirmation dialog.
     * @authors Meylin
     */
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
