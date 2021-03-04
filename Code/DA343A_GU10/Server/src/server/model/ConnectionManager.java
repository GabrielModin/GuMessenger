package server.model;

import shared.Message;
import shared.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ConnectionManager extends Thread {

    private ServerSocket serverSocket;
    ConnectionListener connectionListener;
    MessageListener messageListener;


    public ConnectionManager(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
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
                Connection connection = new Connection(socket, this);

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
