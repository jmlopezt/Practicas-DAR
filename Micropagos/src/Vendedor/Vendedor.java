package Vendedor;

    ///////////////////////////////////////////////////////////////////////\
   ////////////// Juan Manuel López Torralba /////////////////////////////\\\
  //4º Telecomunicaciones - Especialidad en Sistemas de Telecomunicación/\\\\\
 ///////// Asignatura : Diseño de aplicaciones en Red///////////////////\\\\\\\
///////////////////////////////////////////////////////////////////////\\\\\\\\\   

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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

        

/**
 *
 * @author Juanma
 */
public class Vendedor {
    private String secreto="Hortaleza"; // hacer un array para más complejidad
    private String peticion;
    private String articuloDeseado;
    private String pagareID;
    private String valor;   //cambiar nombre
    private String RespuestaFinalACliente;  // cambiar nombre
    private String iDVendedor;
    private String datoscomprador;
    private String fechacaducidad;
    private String hashrecibido;
    private String idpagare;
    
//    private SSLServerSocketFactory factory=(SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
    private SSLServerSocket SocketVendedorListening;
    private SSLSocket SocketVendedorSpeaking;
    
    private BufferedReader in;
    private PrintWriter out;
    
    private Boolean state;
    
    private Map<String,Integer> inventarioStock = new HashMap<>(); 
    MessageDigest HashVendedor=null;
    
    
    
    
    public Vendedor(){
        inventarioStock.put("articulo1", 1);
        inventarioStock.put("articulo2", 3);
        inventarioStock.put("articulo3", 2);
        inventarioStock.put("articulo4", 5);
        inventarioStock.put("articulo5", 4);
        inventarioStock.put("palomitas", 5);
    
        try {
//            SocketVendedorListening=new ServerSocket(6789);  // 
//            SocketVendedorSpeaking=SocketVendedorListening.accept();
                //Creo un Socket SSL SSLServersocket
//           SocketVendedorListening=(SSLServerSocket) factory.createServerSocket(6789);
//           SocketVendedorSpeaking=(SSLSocket) SocketVendedorListening.accept();
                
                
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
            SocketVendedorListening = (SSLServerSocket) factory.createServerSocket(6789);
            SocketVendedorSpeaking=(SSLSocket) SocketVendedorListening.accept();
            
            ///

            out = new PrintWriter(SocketVendedorSpeaking.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(SocketVendedorSpeaking.getInputStream()));
            
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
            peticion=in.readLine();

            if(peticion.equals("SolCompra")){
                System.out.println("Nos solicitan comprar un articulo");
                out.println("ACK");
                peticionVentaArticulo();
            }
            if(peticion.equals("BYE")){
                out.println("Goodbye");
                cerrarSesion();
            }
            
              
        } catch (IOException e) {
            Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    void peticionVentaArticulo(){
        try{
        articuloDeseado=in.readLine();
        pagareID=in.readLine();
        } catch (IOException e){
            Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, e);
        }
        if(CheckingPagare()==true){
            System.out.println("El pagare introducido es verdadero, procedemos con su venta");
            SellArticulo();
            
        }
        else{
            System.out.println("El pagare introducido es falso, no se puede realizar la compra");
        }
        
    
    }
    //arreglar esto
    boolean CheckingPagare(){
       //Creamos una funcion boolean comprobar pagaré, que nos devolverá TRUE si el pagare es cierto, lo cual nos simplifica las cosas.
 
        //El pagare que nos llega es de la forma-->
        //pagare=idvendedor+valorPagareString+datoscomprador+fechacaducidad+idpagare+hash;
        // XX            X              XXX           XXXXXXXXXX      XXXX              HASH///////////////////////
        // 01            2              345           6--------15     16-19             20--LongitudVariable
        //Lo primero que haremos será extraer los datos para poder realizar el hash y comprobar su autenticidad -->
        

        iDVendedor=pagareID.substring(0, 2);   
        valor=pagareID.substring(2,3);
        datoscomprador=pagareID.substring(3, 6);
        fechacaducidad=pagareID.substring(6, 16);
        idpagare=pagareID.substring(16, 20);
        hashrecibido=pagareID.substring(20); //Desde el comienzo en la cadena pagare(20), hasta que esta finaliza

        System.out.println("idvendedor "+iDVendedor);
        System.out.println("valor "+valor);
        System.out.println("datoscomprador "+datoscomprador);
        System.out.println("fechacaducidad "+fechacaducidad);
        System.out.println("idpagare "+idpagare);
        System.out.println("hashrecibido "+hashrecibido);
        
        //AHORA GENERAMOS UN HASH, QUE COMPARAREMOS CON EL HASH INCLUIDO EN EL PAGARE QUE HEMOS RECIBIDO,
        //PARA VER SI EL PAGARE RECIBIDO ES AUTENTICO
        
        boolean autenticidad=false;
        try {
            HashVendedor=MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        byte bytesScrips[]=secreto.getBytes();
            HashVendedor.update(bytesScrips);
            
        byte bytesIDVendedor[]=iDVendedor.getBytes();
            HashVendedor.update(bytesIDVendedor); 
            
        byte bytescantidad[]=valor.getBytes();
            HashVendedor.update(bytescantidad);
            
        byte bytesIdPagare[]=idpagare.getBytes();
            HashVendedor.update(bytesIdPagare);  
            
        byte bytesFechaCaducidad[]=fechacaducidad.getBytes();
            HashVendedor.update(bytesFechaCaducidad);  
            
        byte bytesdatoscomprador[]=datoscomprador.getBytes();
            HashVendedor.update(bytesdatoscomprador);
            
        ////////////////////////////////////////////////////////////////////////////
            
          
        String HashVendedorEnString=new String(HashVendedor.digest()); //Pasamos el resumen a String para compararlo con el obtenido
        System.out.println("Hashgenerado a partir de los datos: "+HashVendedorEnString);
        if(HashVendedorEnString.equals(hashrecibido)){ //Comparamos ambos hash
             autenticidad=true;
         }else{
             autenticidad=false;
         }
            
        return autenticidad;
    }
    
    void SellArticulo(){
        int valorint=Integer.parseInt(valor);
        if(inventarioStock.containsKey(articuloDeseado)){
            if(valorint>=inventarioStock.get(articuloDeseado)){
                String cambio=Integer.toString(valorint-inventarioStock.get(articuloDeseado));//obtengo la diferencia
                RespuestaFinalACliente="La compra se ha realizado correctamente, el articulo "+articuloDeseado+" se ha enviado a la direccion indicada, te han sobrado "+cambio+" €";
            }else{
                RespuestaFinalACliente="Tenemos el árticulo que pides, pero no has ingresado el dinero suficiente, el articulo solicitado vale: "+inventarioStock.get(articuloDeseado)+" € y has introducido "+valor +"€";
            }
        }else{
            RespuestaFinalACliente="No disponemos del artículo solicitado";
        }
        out.println(RespuestaFinalACliente);
    
    }
    
    void cerrarSesion(){
        try {
            in.close();
            out.close();
            SocketVendedorSpeaking.close();      
            System.exit(0);
        } catch (IOException e) {
            Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, e);
        }
    
    }
    
    public static void main(String[] args) {
        new Vendedor();
    }



}


