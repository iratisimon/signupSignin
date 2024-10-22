/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientBusinessLogic;

import logicalExceptions.MaxThreadsErrorException;
import logicalExceptions.ServerErrorException;
import logicalExceptions.SignInErrorException;
import logicalExceptions.UserExistErrorException;
import logicalExceptions.UserNotActiveException;
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

<<<<<<< HEAD
    @Override
=======
    @Override 
>>>>>>> origin/MeylinDev
    public User signIn(User user) throws  MaxThreadsErrorException, ServerErrorException, SignInErrorException,UserNotActiveException {
        Message request  = new Message();
        request.setUser(user);
        request.setMessage(MessageType.SIGN_IN_REQUEST);
        
        Message response = ClientSocket.sendRecieveMessage(request);
        
        if (response == null) {
        throw new ServerErrorException("No response from server.");
        }

        // Creamos una variable para guardar el usuario
        User resultUser = null;
    
        // Aquí podrías manejar la respuesta del servidor y devolver el usuario o null en caso de error
        if (response != null) {
            if (response.getMessage() == MessageType.OK_RESPONSE) {
                resultUser = response.getUser();
            } else if (response.getMessage() == MessageType. MAX_THREADS_ERROR){
                throw new MaxThreadsErrorException("Maximum threads reached. Please wait and try again later.");
            }else if (response.getMessage() == MessageType.SERVER_ERROR){
                throw new ServerErrorException("Internal server error");
            }else if (response.getMessage() == MessageType. SIGN_IN_ERROR){
                throw new SignInErrorException("Error during sign in process.");
            }else if (response.getMessage() == MessageType.  USER_NOT_ACTIVE){
                throw new SignInErrorException("The user is not active");
            }
        }
        return resultUser;
    }

    @Override
    public User signUp(User user) throws ServerErrorException, UserExistErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
