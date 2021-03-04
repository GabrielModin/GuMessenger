package client.model;

import shared.Message;
import shared.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
    private Socket socket;

    public Connection(String ipAddress, int port) throws IOException{
        this.socket = new Socket(ipAddress, port);
        Send send = new Send(socket);
        Receive receive = new Receive(socket);
        send.start();

    }

    class Send extends Thread {
        Socket socket;


        public Send(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.flush();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                User testUser =  new User("Tomten",null);

                oos.writeObject(testUser);
                oos.flush();

                oos.writeObject(new Message(null,null,null,null,null));
                oos.flush();
//                while(true) {
//                    shared.User testRecieve = new shared.User("Gabbe", null);
//                    shared.Message msgToSend = new shared.Message(testUser, testRecieve, new Date());
//
//                    oos.writeObject(msgToSend);
//                    oos.flush();
//                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    class Receive extends Thread {
        Socket socket;
        ObjectOutputStream oos;
        ObjectInputStream ois;

        public Receive(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());

                while (true) {
                    Message msgReceived = (Message) ois.readObject();

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
