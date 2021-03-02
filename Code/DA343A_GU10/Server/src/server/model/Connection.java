package server.model;

import java.io.*;
import java.net.Socket;
import shared.User;
import shared.Message;

public class Connection {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private Socket socket;
    private User user = null;
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

    public synchronized User getUser() throws InterruptedException {
        if(user == null){
            wait();
        }
        return user;
    }
    public synchronized void setUser(User user){
        this.user = user;
        notifyAll();
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

                Object userLogin = inputStream.readObject();
                if (userLogin instanceof User){
                    setUser((User) userLogin);
                } else {
                    System.out.println("Object received from user not instance of User");
                }

                while (true){
                    Object newMessage = inputStream.readObject();
                    if (newMessage instanceof Message){
                        System.out.println("nice");
                    }
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
