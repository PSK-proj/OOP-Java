package org.psk.client.model;

import org.psk.client.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionManager {
  private static Socket socket;
  private static PrintWriter out;
  private static ResponseListener responseListener;
  private static Thread listenerThread;

  public static Socket getSocket() {
    return socket;
  }

  public static void setSocket(Socket socket) throws IOException {
    closeResources();
    ConnectionManager.socket = socket;
    if (socket != null) {
      out = new PrintWriter(socket.getOutputStream(), true);
      responseListener = new ResponseListener(socket);
      listenerThread = new Thread(responseListener);
      listenerThread.start();
    }
  }

  public static void sendTableAssignmentRequest(String macAddress) throws IOException {
    if (out == null) {
      throw new IllegalStateException("PrintWriter is null. Please make sure to connect first.");
    }
    System.out.println("Wysyłanie żądania...");
    String request = "REQUEST_TABLE_ASSIGNMENT:" + macAddress;
    out.println(request);
    out.flush();
  }

  public static void closeResources() {
    if (responseListener != null) {
      responseListener.stop();
    }
    if (out != null) {
      out.close();
    }
    if (socket != null) {
      try {
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static class ResponseListener implements Runnable {
    private volatile boolean running = true;
    private Socket socket;
    private BufferedReader in;

    public ResponseListener(Socket socket) {
      this.socket = socket;
      try {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    @Override
    public void run() {
      String serverMessage;
      try {
        while (running) {
          if (socket.isClosed()) {
            break;
          }
          serverMessage = in.readLine();
          if (serverMessage == null) {
            break;
          }
          String[] messageParts = serverMessage.split(":", 2);
          String responseType = messageParts[0];
          String responseData = messageParts.length > 1 ? messageParts[1] : null;

          switch (responseType) {
            case "TABLE_ASSIGNED":
              assert responseData != null;
              int assignedTableNumber = Integer.parseInt(responseData);
              // aktualizuj numer stolika w aplikacji klienckiej
              System.out.println("Przypisano klientowi nr: "+assignedTableNumber);
              Main.setTableNumber(assignedTableNumber);
              break;
            // obsługa innych typów odpowiedzi
            // ...
          }
        }
      } catch (IOException e) {
        if (running) {
          e.printStackTrace();
        }
      } finally {
        closeResources();
      }
    }

    public void stop() {
      this.running = false;
    }

    private void closeResources() {
      try {
        if (in != null) {
          in.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
