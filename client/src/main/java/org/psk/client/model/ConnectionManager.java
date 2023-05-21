package org.psk.client.model;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionManager {
  private static Socket socket;

  public static Socket getSocket() {
    return socket;
  }

  public static void setSocket(Socket socket) {
    ConnectionManager.socket = socket;
  }

  public static void sendTableAssignmentRequest(String macAddress) throws IOException {
    if (socket == null) {
      throw new IllegalStateException("Socket is null. Please make sure to connect first.");
    }
    System.out.println("Wysyłanie żądania...");
    OutputStream outputStream = socket.getOutputStream();
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));

    String request = "REQUEST_TABLE_ASSIGNMENT:" + macAddress;

    writer.println(request);
    writer.flush();
  }

}

