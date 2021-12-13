package Week_3;

import java.net.*;
import java.io.*;
import java.nio.file.Files;

public class Client {

    public static void main(String [] args) {
        String serverName = "0.0.0.0";
        int port = Integer.parseInt("6060");
        try {
            System.out.println("Подключение к " + serverName + " на порт " + port);
            Socket client = new Socket(serverName, port);
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());

            byte[] buffer = Files.readAllBytes(new File("C:\\Users\\maksum\\Desktop\\test2.txt").toPath());
            out.write(buffer.length);
            out.flush();
            out.write(buffer);
            out.flush();

            System.out.println("Результат запроса " + in.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}