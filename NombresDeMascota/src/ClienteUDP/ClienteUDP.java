/*
 * TAREA 3 DAR OPCIONAL
 * JUAN MANUEL LÓPEZ TORRALBA
 * (basado en el ejemplo ORACULO UDP)
 */
package ClienteUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Juanma
 */
public class ClienteUDP {
    static DatagramSocket socket;
    
    ClienteUDP(){
        String sugerencia;
        String servidor="localhost";
        String sexo;
        
        Scanner sc =new Scanner(System.in);
        System.out.println("Introduzca genero");
        sexo=sc.nextLine();
        
        int puerto=6786;
        
        try {
            // Creamos el socket:
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            System.err.println("Error al abrir el socket.");
        }
                
            enviarSolicitud(sexo,servidor,puerto);
            sugerencia=recibirRespuesta();
            if(sugerencia!=null){
                System.out.println("A la mascota con genero \""+sexo+"\", " +
                        "el Dios de los nombres sugiere: \""+sugerencia+"\"");
                
            }
            
        socket.close();
    }
    
    static int enviarSolicitud(String sexo,String servidor, int puerto){
        int error=0;
        
        // El mensaje lo introducimos en el vector de octetos:
        byte[] octetosMensaje;
        String mensaje;
        InetAddress direccionIP;
        
        // Declaramos el objeto datagrama:
        DatagramPacket paquete;
        
        // Realizamos la traducción de nombre de dominio a dirección IP
        try {
            direccionIP = InetAddress.getByName(servidor); 
        } catch (UnknownHostException e){
            
            error=2;
            return error;
        }
    
        mensaje="Names'sGod,1.1,IwantPetName,"+sexo;
    
        // Creamos el datagrama añadiendo nuestro mensaje, y lo enviamos 
        // "por el socket"
        paquete=new DatagramPacket(mensaje.getBytes(),mensaje.length(),
                        direccionIP,puerto);
        try {
            socket.send(paquete);
        } catch (IOException ex) {
            //ex.printStackTrace();
            error=2;
        }
    
        return error;
    }
    
    static String recibirRespuesta() {
        String [] palabras;
        String consejo;
        String mensaje;
        byte[] octetosMensaje=new byte[1024];
        
        DatagramPacket paquete = new DatagramPacket (octetosMensaje, octetosMensaje.length);
        try {
            socket.receive(paquete);
        } catch (IOException ex) {
            return null;
        }
        
        mensaje=new String(paquete.getData());
        
        palabras=mensaje.split(",");
        
        mensaje=palabras[2];
        
        return mensaje;
    }
    
    public static void main(String[] args){
    
        new ClienteUDP();
    }
}
