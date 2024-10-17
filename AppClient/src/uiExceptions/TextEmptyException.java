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
public class TextEmptyException extends Exception{
     public TextEmptyException(String message) {
        super(message);
    }

    // Método para validar que el campo no esté vacío
    public static void validateNotEmpty(String field, String fieldName) throws TextEmptyException {
        if (field == null || field.trim().isEmpty()) {
            throw new TextEmptyException("The fields cannot be empty.");
        }
    }
}
