/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import java.io.Serializable;

/**
 *
 * @author 2dam
 */
public enum MessageEnum implements Serializable{
    SIGN_IN_REQUEST, SIGN_UP_REQUEST, OK_RESPONSE, SIGN_IN_ERROR, USER_EXISTS_ERROR;
    
    
    
}
