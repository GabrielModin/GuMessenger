package server.model;

import shared.Message;
import shared.User;

import java.io.*;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Connection {

    private Send send;
    private Receive receive;
    private User user;

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private Socket socket;
    private ConnectionController connectionController;

    private Logger logger;

    Connection(Socket socket, ConnectionController connectionController, Logger logger){
        this.socket = socket;
        this.connectionController = connectionController;
        this.logger = logger;

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

    public Socket getSocket() {
        return socket;
    }

    private class Send extends Thread{

        private Buffer<Message> messageBuffer = new Buffer<>();

        public void sendMessage(Message message){
            messageBuffer.put(message);
        }

        @Override
        public void run() {
            try{
                while (true){
                    Message message = messageBuffer.get();
                    outputStream.writeObject(message);
                    outputStream.flush();
                    logger.info(message.getSender() + " sent a message");
                }
            } catch (IOException | InterruptedException e){

            }
        }
    }

    class Receive extends Thread{

        @Override
        public void run() {

            try{
                Object object = inputStream.readObject();

                if (object instanceof User){
                    user = (User) object;
                    connectionController.connectionReceived(user , getConnection());
                    System.out.println(user.getName());
                } else {
                    System.out.println("Object received from user not instance of User");
                }

                while (true){

                    Object message = inputStream.readObject();

                    if (message instanceof Message){
                        connectionController.messageReceived((Message) message);

                        for (User user : ((Message) message).getReceivers()) {
                            logger.info(user.getName() + " received a message"); //Denna loggar received 'ven p[ offline som ej tar emot
                        }
                    }
                }
            } catch (Exception e){
                System.out.println("user disconected");
                connectionController.disconnected(user);
            }
        }
    }
}