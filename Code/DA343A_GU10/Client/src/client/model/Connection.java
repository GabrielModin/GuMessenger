package client.model;

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

    public Connection(String ipAddress, int port) throws IOException{
        this.socket = new Socket(ipAddress, port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        oos.flush();
        ois = new ObjectInputStream(socket.getInputStream());

        Send send = new Send();
        Receive receive = new Receive();
        send.start();
        receive.start();

    }

    class Send extends Thread {

        Buffer<Message> messageBuffer = new Buffer<>();

        @Override
        public void run() {
            try {

                User testUser =  new User("Tomten",null);

                oos.writeObject(testUser);
                oos.flush();

                oos.writeObject(new Message(null,null,null,null,null));
                oos.flush();

                while (true){
                    messageBuffer.get();
                }
//                while(true) {
//                    shared.User testRecieve = new shared.User("Gabbe", null);
//                    shared.Message msgToSend = new shared.Message(testUser, testRecieve, new Date());
//
//                    oos.writeObject(msgToSend);
//                    oos.flush();
//                }
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
                    System.out.println(msgReceived.getMessage());
                    //någon metod för att visa i UI;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
