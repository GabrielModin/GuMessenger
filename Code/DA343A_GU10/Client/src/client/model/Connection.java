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



    public Connection(String ipAddress, int port, User user) throws IOException{
        this.socket = new Socket(ipAddress, port);
        this.user = user;

        oos = new ObjectOutputStream(socket.getOutputStream());
        oos.flush();
        ois = new ObjectInputStream(socket.getInputStream());

        Send send = new Send();
        Receive receive = new Receive();

        send.start();
        User sender = new User("Gabbe",null);
        User receiver = new User("Isak",null);
        User[] arr = new User[1];
        arr[0] = receiver;
        receive.start();

        send.messageBuffer.put(new Message("Det fucking works wtf ",user,arr));
    }

    class Send extends Thread {

        Buffer<Message> messageBuffer = new Buffer<>();

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
                e.printStackTrace();
            }

        }
    }

    class Receive extends Thread {

        @Override
        public void run() {
            try {

                while (true) {
                    Message msgReceived = (Message) ois.readObject();
                    System.out.println(msgReceived.getMessage() + " " + msgReceived.getTimestamp());
                    //någon metod för att visa i UI;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void disconnect() throws IOException {
        socket.close();
    }
}
