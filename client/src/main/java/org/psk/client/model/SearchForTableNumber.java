package org.psk.client.model;

import org.psk.client.Main;
import org.psk.client.model.database.DatabaseConnection;
import org.psk.client.model.database.StolikiDAO;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class SearchForTableNumber {
  private static StolikiDAO stolikiDAO;
  private static Socket clientSocket;

  static {
    stolikiDAO = new StolikiDAO();
    try {
      stolikiDAO.setConnection(DatabaseConnection.getConnection());
    } catch (SQLException | IOException e) {
      e.printStackTrace();
    }
    clientSocket = ConnectionManager.getSocket();
  }

  public static void search() {
    try {
      String macAddress = MacAddressFinder.getMacAddress(clientSocket);
      System.out.println("Adres MAC karty sieciowej używanej do łączenia się z serwerem: " + macAddress);
      Integer tableNumber = stolikiDAO.getStolikByMac(macAddress);
      if (tableNumber != null) {
        System.out.println("Numer stolika: " + tableNumber);
        Main.setTableNumber(tableNumber);
      } else {
        System.out.println("Nie znaleziono numeru stolika dla adresu MAC: " + macAddress);
        ConnectionManager.sendTableAssignmentRequest(macAddress);
      }
    } catch (SQLException | IOException e) {
      e.printStackTrace();
    }
  }
}
