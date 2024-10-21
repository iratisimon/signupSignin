/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiExceptions;

import javafx.scene.control.PasswordField;


/**
 * Custom exception for mismatched passwords.
 * @author Elbire, Meylin
 */
public class PasswdsDontMatchException extends Exception {

    // Constructor without error message
    public PasswdsDontMatchException() {
    }

    // Constructor with a specific error message
    public PasswdsDontMatchException(String msg) {
        super(msg);
    }

    /**
     * Static method to validate if the passwords match.
     * 
     * @param pfHiddenPassword Password field
     * @param pfHiddenConfirmPassword Confirm password field
     * @throws PasswdsDontMatchException If the passwords don't match
     */
    public static void validatePasswords(PasswordField pfHiddenPassword, PasswordField pfHiddenConfirmPassword)
            throws PasswdsDontMatchException {
        if (!pfHiddenPassword.getText().equals(pfHiddenConfirmPassword.getText())) {
            // Throw exception if the passwords don't match
            throw new PasswdsDontMatchException("The passwords don't match");
        }
    }
}
