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
public class Client implements Signable {

    @Override
    public User signIn(User user) {
        Message request  = new Message();
        request.setUser(user);
        request.setMessage(MessageType.SIGN_IN_REQUEST);
        
        Message response = ClientSocket.sendRecieveMessage(request);
        
        // Aquí podrías manejar la respuesta del servidor y devolver el usuario o null en caso de error
        if (response != null) {
            // Procesa la respuesta (puedes agregar lógica para validar la respuesta)
            // Por ejemplo, si la respuesta es OK_RESPONSE, devuelve el usuario
            if (response.getMessage() == MessageType.OK_RESPONSE) {
                return response.getUser();
            } else if (response.getMessage() == MessageType. SIGN_IN_ERROR){
                return null; //throw
            }
        }
        return null;
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
