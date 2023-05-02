package org.psk.client.model;

import org.psk.client.Main;

import java.io.IOException;
import java.sql.SQLException;

public class SearchForTableNumber {
  private static final StolikiDAO stolikiDAO = new StolikiDAO();

  public static void search() {
    String macAddress = getMacAddress();

    try {
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

  private static String getMacAddress() {
    // Implementacja funkcji do pobierania adresu MAC komputera
    return "60-45-CB-9E-FE-FF";
  }
}
