/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.rmi.RemoteException;
import javax.swing.JTextArea;

/**
 *
 * @author Juanma
 */
public class ClientInterfaceImpl implements ClientInterface{
    
    JTextArea chatArea;
    String username;
    
    
    public ClientInterfaceImpl(JTextArea chatArea,String username) throws RemoteException{
        this.chatArea=chatArea;
        this.username=username;
    }

    @Override
    public void showMessage(String message, String username) throws RemoteException {
        if(this.username.compareTo(username)!=0){
            chatArea.append(username+" has said: "+message + "\n");
        }
    }

    @Override
    public void ShowclientState(String username, String state) throws RemoteException {
        String message;
        String newState;
        if (this.username.compareTo(username)==0){
            if (state.equals("Disconnected")){ // Yo me conectado
                
                newState="Connected";
                message=" You are now: "+newState;
            } else {   // Yo me he desconectado
                
                newState="Disconnected";
                message="You are now: "+newState;
            }
        }else{
            if (state.equals("Disconnected")){ // Alguien se ha conectado
                
                newState="Connected";
                message=username+" is now: "+newState;
            } else {  //Alguien se ha desconectado
                
                newState="Disconnected";
                message=username+" is now: "+newState;
            }
        }
        chatArea.append(message+"\n");
    }
    

    
    
}
