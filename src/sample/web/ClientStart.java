package sample.web;

import java.util.Scanner;

public class ClientStart {
    public static void main(String[] args) {
        Client client = new Client();
        System.out.println("Enter your nickname:");
        Scanner scanner= new Scanner(System.in);
        String name= scanner.nextLine();

        while(true){
            client.sendMsg(name);
        }
    }

}
