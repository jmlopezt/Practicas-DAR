package Comprador;

    ///////////////////////////////////////////////////////////////////////\
   ////////////// Juan Manuel López Torralba /////////////////////////////\\\
  //4º Telecomunicaciones - Especialidad en Sistemas de Telecomunicación/\\\\\
 ///////// Asignatura : Diseño de aplicaciones en Red///////////////////\\\\\\\
///////////////////////////////////////////////////////////////////////\\\\\\\\\   


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.Security;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Juanma
 */
public class Comprador {
    
//   private Socket SocketCompradorBroker;
//   private Socket SocketCompradorVendedor;
//   private SSLSocketFactory factory=(SSLSocketFactory) SSLSocketFactory.getDefault();
   private SSLSocket SocketCompradorBroker,SocketCompradorVendedor;
   private PrintWriter outCompradorVendedor,outCompradorBroker;  
   private BufferedReader inCompradorVendedor,inCompradorBroker; 
   private int virtualMoneyPagare; 
   private String iDVendedor,siglasComprador,pagareID;
   private boolean estadoComprador,confirmacionVendedor=false;

    Comprador(){


        try{
            
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            SSLSocketFactory factory=(SSLSocketFactory) SSLSocketFactory.getDefault();
            // creo mis sockets ssl en el cliente
            
            SocketCompradorBroker = (SSLSocket) factory.createSocket("localhost",6788);
            SocketCompradorVendedor = (SSLSocket) factory.createSocket("localhost",6789);
            
            // Le indicamos de qué anillo obtener las claves públicas fiables
            //de autoridades de certificación:
            //
            
            System.setProperty("javax.net.ssl.trustStore","autoridades_certificadoras_fiables.keys");
            
            outCompradorBroker = new PrintWriter(SocketCompradorBroker.getOutputStream(), true);
            inCompradorBroker = new BufferedReader(new InputStreamReader(SocketCompradorBroker.getInputStream()));
            
            outCompradorVendedor = new PrintWriter(SocketCompradorVendedor.getOutputStream(), true);
            inCompradorVendedor = new BufferedReader(new InputStreamReader(SocketCompradorVendedor.getInputStream()));          
            
            ConectarBroker();
          
            if(estadoComprador==true){
                
                SolScrips();
                SolCompra();
            }

        } catch (IOException e){
            Logger.getLogger(Comprador.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    void ConectarBroker(){
        while(estadoComprador == false){
            
            try {
                
                outCompradorBroker.println("HELLO");
                String respuestaBroker=inCompradorBroker.readLine();
                
                if(respuestaBroker.equals("ACK")){
                    estadoComprador=true;
                }
            } catch (IOException e) {
                Logger.getLogger(Comprador.class.getName()).log(Level.SEVERE, null, e);
            }            
        }        
    }
    
    void SolScrips(){
        try {
            
            boolean confirmacion=true;

            virtualMoneyPagare=Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuantos BitCoins quieres?, Máximo 5 €, sin fracciones"));
            iDVendedor="01";               
            siglasComprador="JML";  
            
            outCompradorBroker.println("GiveMeScrips");
            String respuesta=inCompradorBroker.readLine();// ACK
            
            outCompradorBroker.println(virtualMoneyPagare);         //Valor del pagare ) 
            outCompradorBroker.println(siglasComprador);            //Informacion pagador
            outCompradorBroker.println(iDVendedor);                 //Identificador vendedor

            //El identificador y la caducidad del pagare la pone el broker
            
            pagareID=inCompradorBroker.readLine();   
            JOptionPane.showMessageDialog(null, pagareID);
            
            System.out.println("Ya se ha procesado los scrips");
            outCompradorBroker.println("BYE");
            
            //Se cierra la conexion con el broker
            
            outCompradorBroker.close();
            outCompradorBroker.close();
            SocketCompradorBroker.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Comprador.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }

    void SolCompra(){
        
        confirmacionVendedor=false;
        outCompradorVendedor.println("SolCompra");
        try {
            if(inCompradorVendedor.readLine().equals("ACK")){
                confirmacionVendedor=true;
                System.out.println("La compra ha sido aceptada");
            }
        } catch (IOException e) {
            Logger.getLogger(Comprador.class.getName()).log(Level.SEVERE, null, e);
        }
        
        if(confirmacionVendedor==true){
            String articuloDeseado=JOptionPane.showInputDialog("Introduce el articulo que desee");
            outCompradorVendedor.println(articuloDeseado);
            outCompradorVendedor.println(pagareID);
        
            System.out.println("Por favor espere mientras envíamos la informacion y dinero al vendedor");
        
            String respuestafinal=null;     //Respuesta final del vendedor sobre nuestra peticion de compra
            try {
                respuestafinal = inCompradorVendedor.readLine();
            } catch (IOException e) {
                Logger.getLogger(Comprador.class.getName()).log(Level.SEVERE, null, e);
            }
            JOptionPane.showMessageDialog(null, respuestafinal);
        }
        //Cerramos sesión con el vendedor
      
        outCompradorVendedor.println("BYE");
        try {
            
            inCompradorVendedor.close();
            outCompradorVendedor.close();
            SocketCompradorVendedor.close();
        } catch (IOException e) {
            Logger.getLogger(Comprador.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static void main(String[] arg) {
        new Comprador();
   }   
}
