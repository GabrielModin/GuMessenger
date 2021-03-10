package client.model;

import client.controller.MessageClient;
import shared.Message;
import shared.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {

    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private User user;
    private Send send;
    private Receive receive;
    private MessageListener listener;

    public Connection(String ipAddress, int port, User user) throws IOException{
        this.socket = new Socket(ipAddress, port);
        this.user = user;

        oos = new ObjectOutputStream(socket.getOutputStream());
        oos.flush();
        ois = new ObjectInputStream(socket.getInputStream());

        send = new Send();
        receive = new Receive();

        send.start();
        receive.start();


    }

    public void registerMessageListener(MessageListener listener){
        this.listener = listener;
    }

    public void sendMessage(Message message){
        send.addToBuffer(message);
    }


    class Send extends Thread {

        Buffer<Message> messageBuffer = new Buffer<>();

        public void addToBuffer(Message message){
            messageBuffer.put(message);
        }

        @Override
        public void run() {
            try {

                oos.writeObject(user);
                oos.flush();

                while (true){
                    oos.writeObject(messageBuffer.get());
                    oos.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
            }

        }
    }

    class Receive extends Thread {

        @Override
        public void run() {
            try {

                while (true) {
                    Message msgReceived = (Message) ois.readObject();
                    listener.messageReceived(msgReceived);
                }

            } catch (Exception e) {
                System.out.println("disconnected");
            }
        }
    }

    public void disconnect() throws IOException {
        socket.close();
        send.interrupt();
        receive.interrupt();
    }
}
