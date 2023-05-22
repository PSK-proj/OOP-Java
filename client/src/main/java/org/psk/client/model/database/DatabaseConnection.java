package org.psk.client.model.database;

import org.psk.shared.util.ConfigLoadHelper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  private static Connection connection;

  public static Connection getConnection() throws SQLException, IOException {
    if (connection == null || connection.isClosed()) {
      String url = ConfigLoadHelper.getDbUrl();
      String user = ConfigLoadHelper.getClientDbUser();
      String password = ConfigLoadHelper.getClientDbPass();

      connection = DriverManager.getConnection(url, user, password);
    }

    return connection;
  }

  public static void closeConnection() throws SQLException {
    if (connection != null && !connection.isClosed()) {
      connection.close();
    }
  }
}
