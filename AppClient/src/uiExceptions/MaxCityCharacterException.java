/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiExceptions;

import javafx.scene.control.TextField;

/**
 * Custom exception for exceeding maximum city name length.
 * @author Elbire, Meylin
 */
public class MaxCityCharacterException extends Exception {

    // Constructor without error message
    public MaxCityCharacterException() {
    }

    // Constructor with a specific error message
    public MaxCityCharacterException(String msg) {
        super(msg);
    }

    /**
     * Static method to validate the city name length.
     * 
     * @param tfCity City text field
     * @throws MaxCityCharacterException If the city name exceeds 58 characters
     */
    public static void validateCityLength(TextField tfCity) throws MaxCityCharacterException {
        if (tfCity.getText().length() > 58) {
            // Throw exception if the city name length exceeds 58 characters
            throw new MaxCityCharacterException("The city name must be shorter");
        }
    }
}