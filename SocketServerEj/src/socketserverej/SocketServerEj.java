/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketserverej;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author 2dam
 */
public class SocketServerEj {
private final int PUERTO = 5000;
    private final String clave = "abc";

    public void iniciar() {
        ServerSocket servidor = null;
        Socket socket = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;
        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Esperando conexiones del cliente...");
            socket = servidor.accept();
            System.out.println("Cliente conectado");
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
            salida.writeObject("Introduce contraseña:");
            String mensaje = (String) entrada.readObject();
            if (mensaje.equals(clave)) {
                salida.writeObject("Bienvenido.\nQué desear hacer?\n1)Para sumar introduzca: sumar num1 num2\n2)Para multiplicar introduzca: multiplicar num1 num2");
                mensaje = (String) entrada.readObject();
                System.out.println("Recibido: " + mensaje);
                String[] partes = mensaje.split(" ");
                if (((String) partes[0]).equalsIgnoreCase("sumar")) {
                    int a = Integer.parseInt(partes[1]);
                    int b = Integer.parseInt(partes[2]);
                    salida.writeObject(String.valueOf(a + b));
                } else if (((String) partes[0]).equalsIgnoreCase("multiplicar")) {
                    int a = Integer.parseInt(partes[1]);
                    int b = Integer.parseInt(partes[2]);
                    salida.writeObject(String.valueOf(a * b));
                } else {
                    salida.writeObject("ERROR Opción incorrecta");
                }
            } else {
                salida.writeObject("ERROR Contraseña incorrecta.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (servidor != null) {
                    servidor.close();
                }
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
            System.out.println("Fin servidor");
        }
    }

    public static void main(String[] args) {
        SocketServerEj servidor = new SocketServerEj();
        servidor.iniciar();
    }
}
