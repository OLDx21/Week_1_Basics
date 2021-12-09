package Week_3;

import java.net.*;
import java.io.*;

public class Client {

    public static void main(String [] args) {
        String serverName = "0.0.0.0";
        int port = Integer.parseInt("6060");
        try {
            System.out.println("Подключение к " + serverName + " на порт " + port);
            Socket client = new Socket(serverName, port);

            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("3");
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            System.out.println("Результат запроса " + in.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}