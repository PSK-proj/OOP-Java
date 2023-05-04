package org.psk.client.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

  public static Connection getConnection() throws SQLException, IOException {
    Properties properties = new Properties();
    try (InputStream inputStream = DatabaseConnection.class.getResourceAsStream("/org/psk/client/config.properties")) {
      if (inputStream == null) {
        throw new IOException("Nie można znaleźć pliku config.properties");
      }
      properties.load(inputStream);
    }

    String url = properties.getProperty("db.url");
    String user = properties.getProperty("db.user");
    String password = properties.getProperty("db.password");

    return DriverManager.getConnection(url, user, password);
  }
}
