package org.psk.server.controller;

import org.apache.logging.log4j.Logger;
import org.psk.server.model.database.DatabaseConnection;
import org.psk.server.model.database.StolikiDAO;
import org.psk.server.util.LogManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Klasa odpowiedzialna za obsługę połączenia z klientem.
 */
public class ServerSocketHandler implements Runnable {
  private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(ServerSocketHandler.class);
  private final Socket socket;
  private BufferedReader in;
  private PrintWriter out;

  private static final StolikiDAO stolikiDAO;

  static {
    try {
      stolikiDAO = new StolikiDAO();
      stolikiDAO.setConnection(DatabaseConnection.getConnection());
    } catch (SQLException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Konstruktor klasy ustawiający Socket.
   * @param socket
   */
  public ServerSocketHandler(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    try {
      // Inicjalizacja strumieni wejścia i wyjścia
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);

      while (true) {
        if (socket.isClosed()) {
          break;
        }

        String message = in.readLine();

        if (message == null) {
          break;
        }

        String[] messageParts = message.split(":", 2);
        String requestType = messageParts[0];
        String requestData = messageParts.length > 1 ? messageParts[1] : null;

        // Obsługa wiadomości od klienta
        switch (requestType) {
          case "REQUEST_TABLE_ASSIGNMENT":
            // Przetwarzanie żądania przypisania numeru stolika
            String macAddress = requestData;
            logger.debug("Zażądano nr stolika dla: " + macAddress);
            LogManager.getInstance().addLog("Zażądano nr stolika dla: " + macAddress);
            // TU WSTAWIĆ FUNKCJONALNOŚĆ PRZYPISYWANIA STOLIKA W BAZIE DANYCH
            Integer lastNumber = stolikiDAO.getLastTableNumber();
            int assignedTableNumber = lastNumber + 1;
            stolikiDAO.assignTable(macAddress, assignedTableNumber);

            // Wysłanie odpowiedzi do klienta
            out.println("TABLE_ASSIGNED:" + assignedTableNumber);
            logger.debug("Przypisano klientowi: " + macAddress + " numer: " + assignedTableNumber);
            LogManager.getInstance().addLog("Przypisano klientowi: " + macAddress + " numer: " + assignedTableNumber);
            break;
          case "SEND_ORDER":
            // Przetwarzanie wysyłania zamówienia
            break;
          // ...
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      closeConnection();
    }
  }

  /**
   * Metoda zamykająca połączenie.
   */
  public void closeConnection() {
    try {
      // Zamknięcie strumieni wejścia i wyjścia oraz gniazda
      if (in != null) {
        in.close();
      }
      if (out != null) {
        out.close();
      }
      if (socket != null) {
        socket.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
