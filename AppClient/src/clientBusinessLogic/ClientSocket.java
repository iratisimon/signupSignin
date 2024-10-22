package clientBusinessLogic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ResourceBundle;
import logicalModel.message.Message;
import logicalModel.model.User;
import uiExceptions.ServerException;

/**
 * Clase para establecer una conexión con el servidor y enviar un objeto User.
 */
public class ClientSocket {

    public static Message sendRecieveMessage(Message request) {
        Socket socket = null;
        ObjectInputStream read = null;
        ObjectOutputStream write = null;
        Message response = null;

        try {
            ResourceBundle configFile = ResourceBundle.getBundle("config.config");
            String ip = configFile.getString("IP");
            int port = Integer.parseInt(configFile.getString("PORT"));
            
            socket = new Socket(ip, port);
            
            write = new ObjectOutputStream(socket.getOutputStream());
            read = new ObjectInputStream(socket.getInputStream());

            // Enviar el objeto User al servidor
            write.writeObject(request);

            // Recibir respuesta del servidor
            response = (Message) read.readObject();
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (read != null) {
                    read.close();
                }
                if (write != null) {
                    write.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Fin cliente");
        }
        return response;

    }

    public static void main(String[] args) throws ServerException {
        ClientSocket clientSocket = new ClientSocket();
        User user = new User();
    }
}
