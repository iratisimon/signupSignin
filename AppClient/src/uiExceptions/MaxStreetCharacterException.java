package uiExceptions;

import javafx.scene.control.TextField;

/**
 * Custom exception for exceeding maximum street length.
 * @author Elbire,Meylin
 */
public class MaxStreetCharacterException extends Exception {

    // Constructor without error message
    public MaxStreetCharacterException() {
    }

    // Constructor with a specific error message
    public MaxStreetCharacterException(String msg) {
        super(msg);
    }

    /**
     * Static method to validate the street length.
     * 
     * @param tfStreet Street text field
     * @throws MaxStreetCharacterException If the street name exceeds 255 characters
     */
    public static void validateStreetLength(TextField tfStreet) throws MaxStreetCharacterException {
        if (tfStreet.getText().length() > 255) {
            // Throw exception if the street length exceeds 255 characters
            throw new MaxStreetCharacterException("The street must be shorter");
        }
    }
}
