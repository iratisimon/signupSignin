/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientBusinessLogic;

import logicalModel.interfaces.Signable;
import logicalModel.message.Message;
import logicalModel.message.MessageType;
import logicalModel.model.User;


/**
 *
 * @author Olaia, Meylin, Elbire and Irati
 * Implementa la interfaz
 */
public class Client implements Signable{

    @Override
    public User signIn(User user) {
        Message message = new Message();
        User signedInUser = null;
        MessageType serverMessage;
        
        message.setUser(user);
        message.setMessage(MessageType.SIGN_IN_REQUEST);
        
        
        
        return null;
        //aqui viene la vaina de los mensajes 
    }

    @Override
    public User signUp(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeApp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeSession() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
