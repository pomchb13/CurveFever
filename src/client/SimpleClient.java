/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;

/**
 *
 * @author ganleb13
 */
public class SimpleClient {
    
    private static final String HOSTNAME = "localhost";
    private static final int PORTNR = 1234;
    
    public static Object sendRequest(Object request) throws IOException, ClassNotFoundException
    {
        Socket socket = new Socket(HOSTNAME, PORTNR);
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        
        InputStream is = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);
        
        oos.writeObject(request);
        
        Object response = ois.readObject();
        if (response instanceof LinkedList) {
            LinkedList<String> userList = (LinkedList<String>) response;
            return userList;
        }
        ois.close();
        oos.close();
        socket.close();
        
        return response.toString();
    }
    
}
