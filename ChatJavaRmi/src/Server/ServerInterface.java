/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Juanma
 */
public interface ServerInterface extends Remote{
    
    public boolean userRegistration (String username, String password) throws RemoteException;
    public void messageSpreading (String message, String nick) throws RemoteException;
    public void disconnect (String username) throws RemoteException;
    
}
