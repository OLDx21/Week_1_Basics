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

            DatagramSocket i = new DatagramSocket(6060);
            byte[] buffer = Files.readAllBytes(new File("/Users/mdemydovych/Downloads/test2.txt").toPath());
            DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length, new InetSocketAddress("localhost", 6666));
            i.send(datagramPacket);
            i.close();

            InputStream inFromServer = client.getInputStream();

            DataInputStream in = new DataInputStream(inFromServer);

            System.out.println("Результат запроса " + in.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}