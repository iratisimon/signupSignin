package uiExceptions;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Custom exception for empty fields in forms.
 * @author Elbire y Meylin
 */
public class TextEmptyException extends Exception {

    // Constructor without error message
    public TextEmptyException() {
    }

    // Constructor with a specific error message
    public TextEmptyException(String msg) {
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
    public static void checkFieldsSignUp(TextField tfFullName, TextField tfEmail, PasswordField pfHiddenPassword,
                                   PasswordField pfHiddenConfirmPassword, TextField tfStreet, TextField tfCity,
                                   TextField tfZip, TextField tfMobile) throws TextEmptyException {

        if (tfFullName.getText().isEmpty() || tfEmail.getText().isEmpty() || pfHiddenPassword.getText().isEmpty() ||
            pfHiddenConfirmPassword.getText().isEmpty() || tfStreet.getText().isEmpty() || tfCity.getText().isEmpty() ||
            tfZip.getText().isEmpty() || tfMobile.getText().isEmpty()) {

            // Throw exception if any field is empty
            throw new TextEmptyException("You must fill all the parameters");
}}

    /**
     * Validates that the specified field is not empty. If the field is empty,
     * a TextEmptyException is thrown with an appropriate message.
     * 
     * @param field the string to be validated.
     * @param fieldName the name of the field, used in the exception message.
     * @throws TextEmptyException if the field is null or empty.
     */
    public static void checkFieldsSignIn(String email, String passwrd) throws TextEmptyException {
        if (passwrd == null || email.trim().isEmpty()) {
            throw new TextEmptyException("The fields cannot be empty.");

        }
    }
}
