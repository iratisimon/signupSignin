package uiExceptions;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Custom exception for empty fields in forms.
 *
 * @author Elbire y Meylin
 */
public class TextEmptySignUpException extends Exception {

    // Constructor without error message
    public TextEmptySignUpException() {
    }

    // Constructor with a specific error message
    public TextEmptySignUpException(String msg) {
        super(msg);
    }

    /**
     * Static method to validate text fields and password fields.
     *
     * @param tfFullName Full name field
     * @param tfEmail Email field
     * @param pfHiddenPassword Password field
     * @param pfHiddenConfirmPassword Confirm password field
     * @param tfStreet Street field
     * @param tfCity City field
     * @param tfZip Zip code field
     * @param tfMobile Mobile phone field
     * @throws TextEmptyException If any field is empty
     */
    public static void checkFields(TextField tfFullName, TextField tfEmail, PasswordField pfHiddenPassword,
            PasswordField pfHiddenConfirmPassword, TextField tfStreet, TextField tfCity,
            TextField tfZip, TextField tfMobile) throws TextEmptyException {

        if (tfFullName.getText().isEmpty() || tfEmail.getText().isEmpty() || pfHiddenPassword.getText().isEmpty()
                || pfHiddenConfirmPassword.getText().isEmpty() || tfStreet.getText().isEmpty() || tfCity.getText().isEmpty()
                || tfZip.getText().isEmpty() || tfMobile.getText().isEmpty()) {

            // Throw exception if any field is empty
            throw new TextEmptyException("You must fill all the parameters");
        }
    }
}