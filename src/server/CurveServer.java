package server;

import javax.swing.text.JTextComponent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.Vector;

public class CurveServer {
    private Vector<String> users = new Vector<>();
    private LinkedList<ObjectOutputStream> writerList = new LinkedList<>();
    private ServerThread st;
    private JTextComponent logPane;
    private final int PORTNR;

    public CurveServer() {
        this.PORTNR = 1234;
    }

    public CurveServer(int PORTNR) {
        this.PORTNR = PORTNR;
    }


    public CurveServer(JTextComponent logPane, int PORTNR) {
        this(PORTNR);
        this.logPane = logPane;
    }

    public void StartServer() {
        if (st == null || !st.isAlive()) {
            st = new ServerThread();
            st.start();
        }
    }

    public void StopServer() {
        if (st != null && st.isAlive()) {
            st.interrupt();
        }
    }

    protected void log(String logMessage) {
        if (logPane != null) {
            logPane.setText(logPane.getText() + logMessage + "\n");
        } else {
            System.out.println(logMessage);
        }
    }


    class ServerThread extends Thread {

        private ServerSocket server;

        public ServerThread() {
            Thread.currentThread().setPriority(MIN_PRIORITY);
            try {
                Thread.currentThread().setPriority(MIN_PRIORITY);
                server = new ServerSocket(PORTNR);
                server.setSoTimeout(250);
                log("ServerSocket waiting on port" + PORTNR + "...");
            } catch (IOException e) {
                log("Error during starting server: " + e.toString());
            }
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    Socket socket = server.accept();
                    log("Server connected to: " + socket.getRemoteSocketAddress());
                    ClientCommunicationThread cct = new ClientCommunicationThread(socket);
                    Thread thread = new Thread(cct);
                    thread.start();
                } catch (SocketTimeoutException ex) {

                } catch (IOException ex) {
                    log("Error during Server connect");
                }
            }
            try {
                server.close();
                log("shutdown server");
            }catch (IOException ex){
                log("Server shutdown failed");
            }
        }
    }

    class ClientCommunicationThread implements Runnable {

        private Socket socket;

        public ClientCommunicationThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;

            try {

                OutputStream os = socket.getOutputStream();
                oos = new ObjectOutputStream(os);
                oos.flush();
                InputStream is = socket.getInputStream();
                ois = new ObjectInputStream(is);

                writerList.add(oos);

                Object request = ois.readObject();
                String nickname = "";

                if (request instanceof String)
                {
                    nickname = (String) request;
                    log(nickname + "added to clientlist");
                    synchronized (users){
                        users.add(nickname);
                        System.out.println("users added");
                        for (ObjectOutputStream oosWriter : writerList)
                        {
                            oosWriter.reset();
                            oosWriter.writeObject(users);
                        }
                    }
                }
                while (true){
                    String req = (String) ois.readObject();
                    log(req);
                    //Implementierung von serverseitigen Code
                }
            } catch (Exception ex) {

            } finally {
                try {
                    ois.close();
                    oos.close();
                    socket.close();
                } catch (IOException ex) {
                    log("Error during client communication: " + ex.toString());
                }
            }

        }
    }
}

