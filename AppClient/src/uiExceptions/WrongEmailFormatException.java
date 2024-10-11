/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiExceptions;

/**
 *
 * @author 2dam
 */
public class WrongEmailFormatException extends Exception {

    public WrongEmailFormatException(String message) {
        super(message);
    }

    public static void validateEmail(String email) throws WrongEmailFormatException {
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new WrongEmailFormatException("El formato del email es incorrecto.");
        }
    }
}
