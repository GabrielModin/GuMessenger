package server.boundary.connection;

import shared.Message;
import shared.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ConnectionManager extends Thread {

    private ServerSocket serverSocket;
    private HashMap<String, User> connections;

    public ConnectionManager(int port) {
        connections = new HashMap<>();
        try {
            System.out.println("Server : starting server");
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    public void messageReceived(Message message){


    }

    @Override
    public void run(){
        try {
            while (true) {

                Socket socket = serverSocket.accept();

                Connection connection = new Connection(socket, this);

                ClientHandler clientHandler = new ClientHandler(connection);
                clientHandler.start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private class ClientHandler extends Thread{

        private Connection connection;

        public ClientHandler(Connection connection) {
            this.connection = connection;
        }

        @Override
        public void run(){
            try {

                User user = connection.getUser();
                connections.put(user.getName(), user);
                User test = connections.get(user.getName());
                System.out.println(test.getName());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
