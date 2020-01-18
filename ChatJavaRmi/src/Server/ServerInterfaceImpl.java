/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.ClientInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

/**
 *
 * @author Juanma
 */
public class ServerInterfaceImpl implements ServerInterface{
    
    public ServerInterfaceImpl() throws RemoteException{};
    
    ArrayList<String> clients = new ArrayList<>();
    ArrayList<ClientInterface> localClientInterfaces = new ArrayList<>();
    String password="asdf";

    @Override
    public synchronized boolean userRegistration(String username, String contrasenia) throws RemoteException {
        boolean conectado=false;
        if(!clients.contains(username)){
            System.out.println("A client is trying to connect ...");
            
            if(confirmationOfEntry(contrasenia)){
                clients.add(username);
                gettingClientInterface(username);
                SpreadingNewUser(username);
                conectado=true;
            } else{
                System.out.println("Password incorrect ...");
            }
            
        }   
        return conectado;
    }

    @Override
    public synchronized void messageSpreading(String message, String nick) throws RemoteException {
        for(ClientInterface interfaz : localClientInterfaces){
            interfaz.showMessage(message, nick);
        }
    }

    @Override
    public synchronized void disconnect(String username) throws RemoteException {
        int i = clients.indexOf(username);
        if(i!=-1){
            SpreadingDisconnectedUser(username);
            localClientInterfaces.remove(i);
            clients.remove(i);
        } 
    }
    
    private void gettingClientInterface(String username){
        
        try {
            Registry registry = LocateRegistry.getRegistry();
            System.out.println("Seeking Remote object");
            localClientInterfaces.add((ClientInterface) registry.lookup(username));
        } catch (RemoteException | NotBoundException e) {
            System.err.println("An exception in ServerInterfaceImplementation has taken place ... :");
        }

    }
    
    private void SpreadingNewUser(String username) throws RemoteException{
        for(ClientInterface interfaz : localClientInterfaces){
            interfaz.ShowclientState(username,"Disconnected");
        }
    }
    
    private void SpreadingDisconnectedUser(String username) throws RemoteException{
        for(ClientInterface interfaz : localClientInterfaces){
            interfaz.ShowclientState(username,"Connected");
        }
    }
    
    private Boolean confirmationOfEntry (String contrasenia) throws RemoteException{

            return this.password.equals(contrasenia);
    }
    
}
