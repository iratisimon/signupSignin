/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicalModel.message;

import logicalModel.message.MessageType;
import java.io.Serializable;
import logicalModel.model.User;

/**
 *
 * @author 2dam
 */
public class Message implements Serializable{ //para trasmitir el mensaje en forma de bytes a la base de datos
    private User user;
    private MessageType message;
}
