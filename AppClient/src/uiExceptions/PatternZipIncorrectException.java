package uiExceptions;

import javafx.scene.control.TextField;

/**
 * Custom exception for incorrect ZIP code format.
 * @author Elbire, Meylin
 */
public class PatternZipIncorrectException extends Exception {

    // Constructor without error message
    public PatternZipIncorrectException() {
    }

    // Constructor with a specific error message
    public PatternZipIncorrectException(String msg) {
        super(msg);
    }

    /**
     * Static method to validate ZIP code format.
     * 
     * @param tfZip ZIP code text field
     * @throws PatternZipIncorrectException If the ZIP code doesn't follow the 5-digit format
     */
    public static void validateZipFormat(TextField tfZip) throws PatternZipIncorrectException {
        if (!tfZip.getText().matches("\\d{5}$")) {
            // Throw exception if the ZIP code is not a valid 5-digit number
            throw new PatternZipIncorrectException("Zip code must have 5 numeric digits");
        }
    }
}
