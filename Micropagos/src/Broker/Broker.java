package Broker;
    ///////////////////////////////////////////////////////////////////////\
   ////////////// Juan Manuel López Torralba /////////////////////////////\\\
  //4º Telecomunicaciones - Especialidad en Sistemas de Telecomunicación/\\\\\
 ///////// Asignatura : Diseño de aplicaciones en Red///////////////////\\\\\\\
///////////////////////////////////////////////////////////////////////\\\\\\\\\   


import Vendedor.Vendedor;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JOptionPane;



/**
 *
 * @author Juanma
 */
public class Broker {
    
    static String peticion;
    
    static SSLSocket SocketBrokerSpeaking;
    static SSLServerSocket SocketBrokerListening;
    
    static PrintWriter outBroker;
    static BufferedReader inBroker;
    static Boolean estado; //Estado =0 Escuchando   //Estado =1 Conectado con un cliente
    
    private String valorPagareString;
    private String idvendedor;  //string que es un numero de 2 cifras
    private String fechacaducidad="01/01/2015"; //String de 10 cifras
    private String idpagare="0000"; //String de 4 cifras
    private String secreto= "Hortaleza"; // posibilidad de string
    
    private String pagare;
    
    Broker(){


       try {
//          SocketBrokerListening=new ServerSocket(6788);
//          SocketBrokerSpeaking=SocketBrokerListening.accept();
                //Creo un Socket SSL SSLServersocket      
//          SocketBrokerListening=(SSLServerSocket) factory.createServerSocket(6788);
//          SocketBrokerSpeaking=(SSLSocket) SocketBrokerListening.accept();

                /***********************Para SSL*******************************/
                // Para nuestro socket seguro, inicializamos los siguiente:
            SSLContext context = SSLContext.getInstance("SSL");
            
                // La implementaciónn de referencia soporta sólo llaves con formato X.509
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            
                // El tipo de anillo a utilizar será del tipo SUN:
            KeyStore ks = KeyStore.getInstance("JKS");
            
            // Para consultar las llaves almacenadas en el anillo, es necesario dar antes
            // la contrasenia. El problema reside en que al utilizar aquí la clave
            // en texto plano, la seguridad del anillo está comprometida:
            char[] password = "contrasenia_broker".toCharArray(  );
            ks.load(new FileInputStream("soloservidor/anillo_certificado_servidor.keys"), password);
            kmf.init(ks, password);
            
            //
            context.init(kmf.getKeyManagers(  ), null, null);
            SSLServerSocketFactory factory = context.getServerSocketFactory(  );
            SocketBrokerListening = (SSLServerSocket) factory.createServerSocket(6788);
            SocketBrokerSpeaking=(SSLSocket) SocketBrokerListening.accept();

            outBroker = new PrintWriter(SocketBrokerSpeaking.getOutputStream(), true);
            inBroker = new BufferedReader(new InputStreamReader(SocketBrokerSpeaking.getInputStream()));
           
           while(true){
               theWire();
           }
        } catch (SocketTimeoutException | SocketException | UnknownHostException e) {
            e.printStackTrace();

        } catch (IOException e) {
            Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, e);
            System.err.println("Error");
                    /************************** Catchs para errores en SSL ***************************/
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    void theWire(){ 
        try {


        peticion=inBroker.readLine();

        if(peticion.equals("HELLO")){
            outBroker.println("ACK");
            System.out.println("Un comprador ha contactado, devolvemos saludo");
            estado=true;
        }
        if(peticion.equals("GiveMeScrips")){
            outBroker.println("ACK");
            ventaScrips();
        }
        if(peticion.equals("BYE")){
            cerrarSesion();
        }
            
              
        } catch (IOException ex) {
            Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
     void ventaScrips(){
          MessageDigest resumen = null;
       try {
           resumen= MessageDigest.getInstance("MD5");
       } catch (NoSuchAlgorithmException e) {
           Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, e);
       }
         
        
       try {
           int cantidad=0;
           String siglasComprador=null;
           
           valorPagareString =inBroker.readLine();
           siglasComprador=inBroker.readLine();
           idvendedor=inBroker.readLine();    
           
           //GENERACION DE RESUMEN HASH
          byte bytesScrips[]=secreto.getBytes();
            resumen.update(bytesScrips);
            
          byte bytesIdVendedor[]=idvendedor.getBytes();
            resumen.update(bytesIdVendedor); 
            
          byte bytescantidad[]=valorPagareString.getBytes();
            resumen.update(bytescantidad);
            
          byte bytesIdPagare[]=idpagare.getBytes();
            resumen.update(bytesIdPagare);  
            
          byte bytesFechaCaducidad[]=fechacaducidad.getBytes();
            resumen.update(bytesFechaCaducidad);  
            
          byte bytesdatoscomprador[]=siglasComprador.getBytes();
            resumen.update(bytesdatoscomprador);
          
          
           String hash = new String(resumen.digest());
           
///////////////////////////////////////////////////////////////////////////////////////////////////////////////          
///////////////////////////////////////Definicion del pagare///////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////// XX            X              XXX            XXXXXXXXXX      XXXX              HASH       //////
/////////// idvendedor   valorpagare   siglasComprador      fechacaduc     idpagare         resumenHASH    ////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////           
           

           pagare=idvendedor+valorPagareString+siglasComprador+fechacaducidad+idpagare+hash;
           outBroker.println(pagare);
           
           resumen.reset();
           
       } catch (IOException e) {
           Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, e);
       }
            
        
    }
    
    static void cerrarSesion(){
        try {
            System.out.println("Hasta luego, le deseo un buen día");
            outBroker.println("ACK");
            inBroker.close();
            outBroker.close();
            SocketBrokerSpeaking.close();       
            System.exit(0);
        } catch (IOException e) {
            Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static void main(String[] args) {
        new Broker();
     }
    
}
