package sample.web;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    static final int PORT = 3443;
    private ArrayList<ClientHandler>clients = new ArrayList<ClientHandler>();
    public Server(){
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started");
            while(true){
                clientSocket= serverSocket.accept();
                ClientHandler client = new ClientHandler(clientSocket,this);
                clients.add(client);
                new Thread(client).start();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
            clientSocket.close();
            System.out.println("Server closed");
            serverSocket.close();
        }catch (IOException ex){
            ex.printStackTrace();

        }
    }
}
public void sendMessageToAllClients(String msg){
for(ClientHandler o: clients){
        o.sendMsg(msg);
        }
        }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }
}

