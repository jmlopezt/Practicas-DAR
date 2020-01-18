/*
 * TAREA 3 DAR OPCIONAL
 * JUAN MANUEL LÓPEZ TORRALBA
 * (basado en el ejemplo ORACULO UDP)
 */
package ServerUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Juanma
 */
public class ServerUDP {
    
    static DatagramSocket socket = null;
    static InetAddress solicitanteIP;
    static int solicitantePuerto;
    
    
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
    
    static String  recibirSolicitud() {
        String pregunta=null;
        String mensaje;
        String [] palabras;
        DatagramPacket paquete;
        byte [] octetosMensaje=new byte[1024];
        
        // recibir solicitud:
        paquete = new DatagramPacket(octetosMensaje, octetosMensaje.length);
        try {
            socket.receive(paquete);
        } catch (IOException ex) {
            //ex.printStackTrace();
            return null;
        }
        
        // Se procesa el mensaje recibido:
        mensaje=new String(paquete.getData());
        palabras=mensaje.split(",");
        
        // Si el mensaje es de solicitud:
        if(palabras[2].compareTo("IwantPetName")==0){
            pregunta=new String(palabras[3]);
            solicitanteIP=paquete.getAddress();
            solicitantePuerto=paquete.getPort();
        }
        
        return pregunta;
    }
    
     private static int enviarRespuesta(String sugerencia, 
            InetAddress solicitanteIP, int solicitantePuerto) {
       int error=0;
        
        // El mensaje lo introducimos en el vector de octetos:
        byte[] octetosMensaje;
        String mensaje;
                
        // Declaramos el objeto datagrama:
        DatagramPacket paquete;
    
        mensaje="Names'sGod/1.1,ok,"+sugerencia;
    
        // Creamos el datagrama a?adiendo nuestro mensaje, y lo enviamos 
        // "por el socket"
        paquete=new DatagramPacket(mensaje.getBytes(),mensaje.length(),
                        solicitanteIP,solicitantePuerto);
        try {
            socket.send(paquete);
        } catch (IOException ex) {
            ex.printStackTrace();
            error=2;
        }
    
        return error;
    }
    
    
    ServerUDP(){
        
        String peticion=null;
        String sugerencia=null;
        boolean salir=false;
        int error=0;
        int puerto=6786;
        String sexo=null;
    
        try {    
            // Se crea el socket que leer? del puerto indicado:
            socket = new DatagramSocket(puerto);
        } catch (SocketException ex) {
            ex.printStackTrace();
            System.err.println("Error al abrir el socket.");
        }
        
        GeneraNombres oraculo=new GeneraNombres();
         do{
                peticion=recibirSolicitud();
                
                if(peticion!=null){
                    if (peticion.contains("macho")){
                        
                        sugerencia=oraculo.obtenerPrediccionMacho();
                    }
                    else{
                        sugerencia=oraculo.obtenerPrediccionHembra();    
                    }
                    enviarRespuesta(sugerencia,solicitanteIP,solicitantePuerto);
                    
                } else {
                    salir=true;
                }
                
            } while(!salir);
                
    }
    
    public static void main(String[] args){
    
        new ServerUDP();
    }
    
}
