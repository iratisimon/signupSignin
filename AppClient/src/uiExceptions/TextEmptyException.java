/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiExceptions;

/**
 * The TextEmptyException class is a custom exception that is thrown
 * when a text field is found to be empty.
 * 
 * This exception extends the Exception class and provides a method
 * to validate that a given text field is not empty.
 * 
 * @author Irati
 */
public class TextEmptyException extends Exception{
    
    /**
     * Constructs a new TextEmptyException with the specified detail message.
     * 
     * @param message the detail message.
     */
     public TextEmptyException(String message) {
        super(message);
    }

    /**
     * Validates that the specified field is not empty. If the field is empty,
     * a TextEmptyException is thrown with an appropriate message.
     * 
     * @param field the string to be validated.
     * @param fieldName the name of the field, used in the exception message.
     * @throws TextEmptyException if the field is null or empty.
     */
    public static void validateNotEmpty(String field, String fieldName) throws TextEmptyException {
        if (field == null || field.trim().isEmpty()) {
            throw new TextEmptyException("The fields cannot be empty.");
        }
    }
}
