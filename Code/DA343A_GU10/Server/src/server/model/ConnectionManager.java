package server.model;

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

    @Override
    public void run(){
        try {
            while (true) {

                Socket socket = serverSocket.accept();
                Connection connection = new Connection(socket, this);
                ClientAuthenticator clientAuthenticator = new ClientAuthenticator(connection);
                clientAuthenticator.start();

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private class ClientAuthenticator extends Thread{

        private Connection connection;

        public ClientAuthenticator(Connection connection) {
            this.connection = connection;
        }

        @Override
        public void run(){
            try {
                User user = connection.getUser();
                connectionListener.newConnection(user, connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
