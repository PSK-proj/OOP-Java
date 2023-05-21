package org.psk.client.model;

import org.psk.shared.util.ConfigLoadHelper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

  public static Connection getConnection() throws SQLException, IOException {

    String url = ConfigLoadHelper.getDbUrl();
    String user = ConfigLoadHelper.getClientDbUser();
    String password = ConfigLoadHelper.getClientDbPass();

    return DriverManager.getConnection(url, user, password);
  }
}
