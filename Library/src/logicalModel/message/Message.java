/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicalModel.message;

import java.io.Serializable;
import logicalModel.model.User;

/**
 *
 * @author 2dam
 */
public class Message implements Serializable{ //para trasmitir el mensaje en forma de bytes a la base de datos
    private User user;
    private MessageType message;

    public Message(User user, MessageType message) {
        this.user = user;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MessageType getMessage() {
        return message;
    }

    public void setMessage(MessageType message) {
        this.message = message;
    }
}
