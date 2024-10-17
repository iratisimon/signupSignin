package clientBusinessLogic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import logicalModel.model.User;
import uiExceptions.ServerException;

/**
 * Clase para establecer una conexión con el servidor y enviar un objeto User.
 */
public class ClientSocket {
    private final int PORT=8069 ;
    private final String IP="127.0.0.1";

    
    public void iniciar(User user) {
        Socket socket = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;
        
        try {
            socket = new Socket(IP,PORT);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
             
            
            System.out.println("Conexión establecida con el servidor en " + IP + ":" + PORT);
            
            // Enviar el objeto User al servidor
            salida.writeObject(user);
            System.out.println("Usuario enviado al servidor: " + user.getEmail());
            
            // Recibir respuesta del servidor
            String respuesta = (String) entrada.readObject();
            System.out.println("Respuesta del servidor: " + respuesta);
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws ServerException {
        ClientSocket clientSocket = new ClientSocket();
        User user = new User();
        clientSocket.iniciar(user);
    }
}
