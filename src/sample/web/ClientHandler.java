package sample.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Server server;
    private PrintWriter outMassage;
    private Scanner inMessage;

    private static final String HOST = "192.168.88.77";
    private static final int PORT = 3443;

    private Socket clientSocket;

    public ClientHandler(Socket socket, Server server) {
        try {
            this.server = server;
            this.clientSocket = socket;
            this.outMassage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (IOException e) {
        e.printStackTrace();
    }


    }

    @Override
    public void run(){
        try{
            while(true){
                server.sendMessageToAllClients("New user was connected");
                break;
            }
            while(true){
                if(inMessage.hasNext()){
                    String clientMessage = inMessage.nextLine();
                    if(clientMessage.equalsIgnoreCase("##session##end##")){
                        break;
                    }
                    server.sendMessageToAllClients(clientMessage);
                }
            }
            Thread.sleep(100);

        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            this.close();
        }
        }
        public void sendMsg(String msg){
        try {
            outMassage.println(msg);
            outMassage.flush();
        }catch(Exception e){
            e.printStackTrace();

        }

    }
    public void close(){
        server.removeClient(this);
    }
}
