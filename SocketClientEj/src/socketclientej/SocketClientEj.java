/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketclientej;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author 2dam
 */
public class SocketClientEj {
private final int PUERTO = 5000;
    private final String IP = "127.0.0.1";

    public void iniciar() {
        Socket socket = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;
        try {
            socket = new Socket(IP, PUERTO);
            System.out.println("Esperando que el servidor envíe algo....");
            entrada = new ObjectInputStream(socket.getInputStream());
            salida = new ObjectOutputStream(socket.getOutputStream());
            
            String mensaje = (String) entrada.readObject();
            System.out.println("Conexión realizada con servidor");
            System.out.println(mensaje);
            Scanner input = new Scanner(System.in);
            String clave = input.nextLine();
            salida.writeObject(clave);
            mensaje = (String) entrada.readObject();
            System.out.println(mensaje);
            if (!mensaje.substring(0, 5).equalsIgnoreCase("ERROR")) {
                String opcion = input.nextLine();
                salida.writeObject(opcion);
                mensaje = (String) entrada.readObject();
                System.out.println(mensaje);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
                if (salida != null) {
                    salida.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Fin cliente");
        }
    }

    public static void main(String[] args) {
        SocketClientEj unCliente = new SocketClientEj();
        unCliente.iniciar();
    }
}
