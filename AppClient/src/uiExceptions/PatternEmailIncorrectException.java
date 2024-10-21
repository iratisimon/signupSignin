package uiExceptions;

import javafx.scene.control.TextField;
import java.util.regex.Pattern;

/**
 * Custom exception for invalid email patterns.
 * Authors: Elbire, Meylin 
 */
public class PatternEmailIncorrectException extends Exception {

    // Constructor without error message
    public PatternEmailIncorrectException() {
    }

    // Constructor with a specific error message
    public PatternEmailIncorrectException(String msg) {
        super(msg);
    }

    /**
     * Static method to validate the email field.
     * 
     * @param tfEmail Email text field
     * @throws PatternEmailIncorrectException If the email format is invalid or length exceeds 320 characters
     */
    public static void validateEmail(TextField tfEmail) throws PatternEmailIncorrectException {
        if (!Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", tfEmail.getText()) 
            || tfEmail.getText().length() > 320) {

            // Throw the exception if the email format is invalid or too long
            throw new PatternEmailIncorrectException("The email must have a valid format");
        }
    }
}
