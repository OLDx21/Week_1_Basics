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
    while (true) {
      try {
        System.out.println("Ожидание клиента на порт " +
            serverSocket.getLocalPort() + "...");
        Socket server = serverSocket.accept();

        System.out.println("Просто подключается к " + server.getRemoteSocketAddress());
        ObjectInputStream in = new ObjectInputStream(server.getInputStream());
        DataOutputStream out = new DataOutputStream(server.getOutputStream());
        OutputStream output = new FileOutputStream("test.txt");
        int fileSize = in.read();

        int bytesRead;
        byte[] buffer = new byte[fileSize];

        for (int i =0; i<buffer.length; i++){
          bytesRead = in.read(buffer);
          output.write(buffer, 0, bytesRead);
        }
        // Closing the FileOutputStream handle
        output.close();
        out.writeUTF(getDataFromStorage(buffer));
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

  public static void main(String[] args) {
    int port = Integer.parseInt("6060");
    try {
      Thread t = new Server(port);
      t.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String getDataFromStorage(byte[] file) {
    String s = new String(file);
    HashStorage hashStorage = HashStorage.getInstance();
    return hashStorage.getDataByKey(s);
  }
}