/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiExceptions;

import javafx.scene.control.TextField;

/**
 * Custom exception for incorrect mobile number format.
 *
 * @author Elbire, Meylin
 */
public class PatternMobileIncorrectException extends Exception {

    // Constructor without error message
    public PatternMobileIncorrectException() {
    }

    // Constructor with a specific error message
    public PatternMobileIncorrectException(String msg) {
        super(msg);
    }

    /**
     * Static method to validate mobile number format.
     *
     * @param tfMobile Mobile number text field
     * @throws PatternMobileIncorrectException If the mobile number is not 9
     * digits
     */
    public static void validateMobileFormat(TextField tfMobile) throws PatternMobileIncorrectException {
        if (!tfMobile.getText().matches("\\d{9}$")) {
            // Throw exception if the mobile number is not 9 digits
            throw new PatternMobileIncorrectException("Phone number must have 9 numeric digits");
        }
    }
}
