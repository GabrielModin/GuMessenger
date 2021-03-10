package server.model;

import shared.Message;
import shared.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class ConnectionController extends Thread {

    private ServerSocket serverSocket;
    private ConnectionManager connectionManager;
    private ConnectionListener connectionListener;
    private MessageListener messageListener;

    private FileHandler fh;
    private Logger logger = Logger.getLogger(Connection.class.getName());


    public ConnectionController(int port) {

        connectionManager = new ConnectionManager();

        registerConnectionListener(connectionManager);
        registerMessageListener(connectionManager.getMessageListener());

        try {
            fh = new FileHandler("Code/DA343A_GU10/files/TrafficLog.log");
            logger.addHandler(fh);

            serverSocket = new ServerSocket(port);
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerMessageListener(MessageListener listener){
        this.messageListener = listener;
    }

    public void registerConnectionListener(ConnectionListener listener){
        this.connectionListener = listener;
    }

    public void messageReceived(Message message) {
        messageListener.messageReceived(message);
    }

    public void connectionReceived(User user, Connection connection){
        connectionListener.newConnection(user,connection);
    }

    @Override
    public void run(){
        try {
            while (true) {

                Socket socket = serverSocket.accept();
                Connection connection = new Connection(socket, this, logger);

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void disconnected(User user) {
        connectionManager.connectionClosed(user);
    }
}
