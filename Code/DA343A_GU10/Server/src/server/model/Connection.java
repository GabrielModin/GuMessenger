package server.model;

import java.io.*;
import java.net.Socket;
import shared.User;
import shared.Message;

public class Connection {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private Socket socket;
    private ConnectionManager connectionManager;

    Connection(Socket socket, ConnectionManager connectionManager){
        this.socket = socket;

        try {
            outputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            outputStream.flush();
            inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Send send = new Send();
        Receive receive = new Receive();

        send.start();
        receive.start();
    }

    public Connection getConnection(){
        return this;
    }

    class Send extends Thread{

        @Override
        public void run() {
            try{
                while (true){
                    outputStream.flush();
                    return;
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    class Receive extends Thread{

        @Override
        public void run() {
            try{

                Object object = inputStream.readObject();
                if (object instanceof User){
                    User user = (User) object;
                    connectionManager.connectionReceived(user , getConnection());
                } else {
                    System.out.println("Object received from user not instance of User");
                }

                while (true){
                    Object message = inputStream.readObject();
                    if (message instanceof Message){
                        connectionManager.messageReceived((Message) message);
                    }
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
