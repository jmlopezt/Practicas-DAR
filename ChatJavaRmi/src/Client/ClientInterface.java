/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Juanma
 */
public interface ClientInterface extends Remote{
    
    public void showMessage (String message, String username) throws RemoteException;
    public void ShowclientState (String username, String state) throws RemoteException;
    
}
