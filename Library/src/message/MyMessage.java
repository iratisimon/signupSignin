/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import java.io.Serializable;
import model.User;

/**
 *
 * @author 2dam
 */
public class MyMessage implements Serializable{ //para trasmitir el mensaje en forma de bytes a la base de datos
    private User user;
    private MessageEnum message;
}
