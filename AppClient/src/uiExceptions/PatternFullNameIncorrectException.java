/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiExceptions;

import javafx.scene.control.TextField;
import java.util.regex.Pattern;

/**
 * Custom exception for invalid full name patterns.
 * @author Elbire y Meylin
 */

public class PatternFullNameIncorrectException extends Exception {

    // Constructor without error message
    public PatternFullNameIncorrectException() {
    }

    // Constructor with a specific error message
    public PatternFullNameIncorrectException(String msg) {
        super(msg);
    }

    /**
     * Static method to validate the full name field.
     * 
     * @param tfFullName Full name text field
     * @throws PatternFullNameIncorrectException If the full name contains numbers or invalid characters
     */
    public static void validateFullName(TextField tfFullName) throws PatternFullNameIncorrectException {
        if (!Pattern.matches("^[A-Za-zÀ-ÿ'\\s]+$", tfFullName.getText())) {
            // Throw the exception if the full name contains numbers or invalid characters
            throw new PatternFullNameIncorrectException("The full name can't contain numbers");
        }
    }
}
