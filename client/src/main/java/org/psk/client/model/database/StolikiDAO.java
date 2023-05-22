package org.psk.client.model.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StolikiDAO {

  public Integer getStolikByMac(String macAddress) throws SQLException, IOException {
    String query = "SELECT numer FROM stoliki WHERE mac = ?";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

      preparedStatement.setString(1, macAddress);

      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          return resultSet.getInt("numer");
        } else {
          return null;
        }
      }
    }
  }
}
