package org.psk.client.model;

import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.psk.client.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Klasa odpowiedzialna za połączenie klienta z serwerem.
 */
public class ConnectionManager {
  private static final Logger logger = LogManager.getLogger(ConnectionManager.class);
  private static Socket socket;
  private static PrintWriter out;
  private static ResponseListener responseListener;
  private static Thread listenerThread;

  /**
   * Getter na socket
   * @return socket
   */
  public static Socket getSocket() {
    return socket;
  }

  /**
   * Setter na socket
   * @param socket
   * @throws IOException
   */
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

  /**
   * Metoda wysyłające request o przypisanie numeru stolika.
   * @param macAddress Adres MAC maszyny.
   * @throws IOException
   */
  public static void sendTableAssignmentRequest(String macAddress) throws IOException {
    if (out == null) {
      throw new IllegalStateException("PrintWriter is null. Please make sure to connect first.");
    }
    logger.info("Wysyłanie żądania...");
    String request = "REQUEST_TABLE_ASSIGNMENT:" + macAddress;
    out.println(request);
    out.flush();
  }

  /**
   * Metoda zamykająca połączenie z serwerem.
   */
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

  /**
   * Klasa obsługująca odpowiedzi od serwera.
   */
  private static class ResponseListener implements Runnable {
    private volatile boolean running = true;
    private final Socket socket;
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
              logger.debug("Przypisano klientowi nr: " + assignedTableNumber);
              Platform.runLater(() -> {
                Main.setTableNumber(assignedTableNumber);
              });
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

    /**
     * Metoda zamykająca połączenie.
     */
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
