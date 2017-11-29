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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 *
 * @author ganleb13
 */
public class ClientThread extends Thread {

    private final String HOSTNAME = "localhost";
    private final int PORTNR = 1234;
    private String nickname;
    private JTextArea taMessages;
    private JList list;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public ClientThread(String nickname, JTextArea taArea, JList list) throws IOException {
        this.nickname = nickname;
        this.taMessages = taArea;
        this.list = list;

        Socket socket = new Socket(HOSTNAME, PORTNR);
        OutputStream os = socket.getOutputStream();
        oos = new ObjectOutputStream(os);
        oos.flush();
        InputStream is = socket.getInputStream();
        ois = new ObjectInputStream(is);

    }

    @Override
    public void run() {
        try {
            oos.writeObject(nickname);
            while (true) {
                Object response = ois.readObject();
                if (response instanceof Vector) {
                    Vector<String> userList = (Vector<String>) response;

                    list.setListData(userList);
                } else if (response instanceof String) {
                    
                    //Implement clientseitigen Code
                    
                    String nick = ((String) response).split("0")[0];
                    String text = ((String) response).split("0")[1];
                    taMessages.setText(nick +": "+text);
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        list.setListData(new Vector());
        
    }
    
    public void sendMessage(String message) throws IOException
    {
        oos.writeObject(message);
    }

    public String getHostname() {
        return HOSTNAME;
    }

}
