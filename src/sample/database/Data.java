package sample.database;

import javax.xml.transform.Result;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Data extends  Config {
    private Scanner scanner = new Scanner(System.in);
    private Connection connection = null;
    public Data(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(HOST, USER, PASS);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String connect(String login) {
        String result = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);
            PreparedStatement select =  connection.prepareStatement("SELECT pass FROM users WHERE login=? ");
            select.setString(1,login);
            ResultSet list = select.executeQuery();
            System.out.println("Success");
            while (list.next()){
                result = list.getString(1);

              //  System.out.println(list.getInt(1) + "  " + list.getString(2) + "  " + list.getString(3));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return result;

    }
    public void auth(){
        String Login = scanner.nextLine();
        String password = scanner.nextLine();
        if (password.equals(connect(Login))) {
            System.out.println("Авторизован");
        }else{
                System.out.println("Неверный пароль");
            }

        }
        public void insertNewMassage (String name , String message){
            try {


                PreparedStatement insert  = connection.prepareStatement("INSERT INTO messages (username , message)" + "VALUES(?,?)");

                insert.setString(1,name);
                insert.setString(2,message);
                insert.executeUpdate();
                System.out.println("Success Insert");
            }catch (Exception e) {
                e.printStackTrace();
            }



        }
    }

