package org.psk.client.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.psk.client.Main;
import org.psk.client.model.database.DatabaseConnection;
import org.psk.client.model.database.StolikiDAO;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class SearchForTableNumber {
  private static final Logger logger = LogManager.getLogger(SearchForTableNumber.class);
  private static final StolikiDAO stolikiDAO;
  private static final Socket clientSocket;

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
      logger.debug("Adres MAC karty sieciowej używanej do łączenia się z serwerem: " + macAddress);
      Integer tableNumber = stolikiDAO.getStolikByMac(macAddress);
      if (tableNumber != null) {
        logger.debug("Numer stolika: " + tableNumber);
        Main.setTableNumber(tableNumber);
      } else {
        logger.debug("Nie znaleziono numeru stolika dla adresu MAC: " + macAddress);
        ConnectionManager.sendTableAssignmentRequest(macAddress);
      }
    } catch (SQLException | IOException e) {
      e.printStackTrace();
    }
  }
}
