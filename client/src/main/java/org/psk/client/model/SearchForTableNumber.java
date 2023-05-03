package org.psk.client.model;

import org.psk.client.Main;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class SearchForTableNumber {
  private static final StolikiDAO stolikiDAO = new StolikiDAO();
  private static final Socket clientSocket = ConnectionManager.getSocket();

  public static void search() {
    try {
      String macAddress = MacAddressFinder.getMacAddress(clientSocket);
      System.out.println("Adres MAC karty sieciowej używanej do łączenia się z serwerem: " + macAddress);
      Integer tableNumber = stolikiDAO.getStolikByMac(macAddress);
      if (tableNumber != null) {
        // Znaleziono numer stolika dla danego adresu MAC
        System.out.println("Numer stolika: " + tableNumber);
        Main.setTableNumber(tableNumber);
      } else {
        // Nie znaleziono numeru stolika dla danego adresu MAC, obsłużyć tę sytuację
        System.out.println("Nie znaleziono numeru stolika dla adresu MAC: " + macAddress);
      }
    } catch (SQLException | IOException e) {
      // Obsługa wyjątków, np. problemy z połączeniem z bazą danych
      e.printStackTrace();
    }
  }
}
