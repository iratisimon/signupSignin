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
public class InvalidConfirmPassword extends Exception{
    public InvalidConfirmPassword(String message) {
        super(message);
    }
}
