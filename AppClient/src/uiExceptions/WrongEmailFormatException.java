/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiExceptions;

/**
 * The WrongEmailFormatException class is a custom exception that is thrown
 * when an email address is found to be in an incorrect format.
 * 
 * This exception extends the Exception class and provides a method
 * to validate the format of an email address.
 * 
 * @author Irati
 */
public class WrongEmailFormatException extends Exception {
    /**
     * Constructs a new WrongEmailFormatException with the specified detail message.
     * 
     * @param message the detail message.
     */
    public WrongEmailFormatException(String message) {
        super(message);
    }
    
    /**
     * Validates the format of the specified email address. If the email
     * does not match the expected format, a WrongEmailFormatException is thrown
     * with an appropriate message.
     * 
     * @param email the email address to be validated.
     * @throws WrongEmailFormatException if the email format is incorrect.
     */
    public static void validateEmail(String email) throws WrongEmailFormatException {
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new WrongEmailFormatException("The email format is incorrect.");
        }
    }
}
