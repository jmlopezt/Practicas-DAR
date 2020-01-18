/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Juanma
 */
public class Server {
    
    public static void main(String[] args) {
            //Incluye el gestor de seguridad
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }       
        
        try {
            Registry r = LocateRegistry.createRegistry(1099);
            ServerInterface objExportado = (ServerInterface) new ServerInterfaceImpl();
            ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(objExportado,0);
            r.rebind("objeto_servidor", stub); 
            System.out.println("Server is connected and ready for operation.");
        } catch (Exception e) {
            System.out.println("Server not connected: " + e);
        }
    }
}
