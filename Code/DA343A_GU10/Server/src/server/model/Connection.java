package server.model;

import java.io.*;
import java.net.Socket;
import shared.User;
import shared.Message;

public class Connection {

    Send send;
    Receive receive;

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private Socket socket;
    private ConnectionController connectionController;

    Connection(Socket socket, ConnectionController connectionController){
        this.socket = socket;
        this.connectionController = connectionController;

        try {
            outputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            outputStream.flush();
            inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        send = new Send();
        receive = new Receive();

        send.start();
        receive.start();
    }

    public void sendMessage(Message message){
        send.sendMessage(message);
    }

    public Connection getConnection(){
        return this;
    }

    private class Send extends Thread{

        Buffer<Message> messageBuffer = new Buffer<>();

        public void sendMessage(Message message){
            messageBuffer.put(message);
        }

        @Override
        public void run() {
            try{
                while (true){
                    outputStream.writeObject(messageBuffer.get());
                    outputStream.flush();
                }
            } catch (IOException e){
                e.printStackTrace();
            } catch (InterruptedException e) {
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
                    connectionController.connectionReceived(user , getConnection());
                    System.out.println(user.getName());
                } else {
                    System.out.println("Object received from user not instance of User");
                }

                while (true){
                    Object message = inputStream.readObject();

                    if (message instanceof Message){
                        connectionController.messageReceived((Message) message);
                    }

                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
