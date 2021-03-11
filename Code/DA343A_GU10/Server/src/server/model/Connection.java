package server.model;

import server.controller.ConnectionController;
import shared.Message;
import shared.User;

import java.io.*;
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

    public Connection(Socket socket, ConnectionController connectionController, Logger logger) {
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

    private class Send extends Thread {
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

                    logger.info(user.getName() + " received a message from " + message.getSender());
                }
            } catch (IOException | InterruptedException e){
            }
        }
    }

    class Receive extends Thread {

        @Override
        public void run() {
            try{
                Object object = inputStream.readObject();

                if (object instanceof User){
                    user = (User) object;
                    connectionController.connectionReceived(user , getConnection());
                    logger.info(user.getName() + " connected");
                } else {
                    return;
                }

                while (true){
                    Object message = inputStream.readObject();

                    if (message instanceof Message){
                        Message msg = (Message) message;
                        connectionController.messageReceived(msg);

                        logger.info(msg.getSender() + " sent a message to " + user.getName());

                    } else {
                        return;
                    }
                }
            } catch (Exception e){
                logger.info(user.getName() + "disconnected");
                connectionController.disconnected(user);
            }
        }
    }
}