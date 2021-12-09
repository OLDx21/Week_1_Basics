package Week_3;

import Week_2.HashStorage;

import java.net.*;
import java.io.*;

public class Server extends Thread {
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(60000);
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println("Ожидание клиента на порт " +
                        serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();

                System.out.println("Просто подключается к " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());

                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF(getDataFromStorage(in.readUTF()));
                server.close();

            } catch (SocketTimeoutException s) {
                System.out.println("Время сокета истекло!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String [] args) {
        int port = Integer.parseInt("6060");
        try {
            Thread t = new Server(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getDataFromStorage(String key){
        HashStorage hashStorage = HashStorage.getInstance();
        return hashStorage.getDataByKey(key);
    }
}