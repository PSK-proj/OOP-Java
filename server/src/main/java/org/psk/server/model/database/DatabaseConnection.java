package org.psk.server.model.database;

import org.psk.shared.util.ConfigLoadHelper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 /**
 * Klasa obsługująca połączenie z bazą danych.
 */
public class DatabaseConnection {
  private static Connection connection;

   /**
    * Nawiązywanie połączenia z bazą danych.
    * @return Uchwyt połączenia do bazy danych.
    * @throws SQLException
    * @throws IOException
    */
  public static Connection getConnection() throws SQLException, IOException {
    if (connection == null || connection.isClosed()) {
      String url = ConfigLoadHelper.getDbUrl();
      String user = ConfigLoadHelper.getServerDbUser();
      String password = ConfigLoadHelper.getServerDbPass();

      connection = DriverManager.getConnection(url, user, password);
    }

    return connection;
  }

   /**
    * Zamykanie połączenia z bazą danych.
    * @throws SQLException
    */
  public static void closeConnection() throws SQLException {
    if (connection != null && !connection.isClosed()) {
      connection.close();
    }
  }
}