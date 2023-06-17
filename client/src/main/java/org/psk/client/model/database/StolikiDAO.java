package org.psk.client.model.database;

import org.psk.shared.database.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StolikiDAO implements DAO {
  private Connection connection;
  @Override
  public void setConnection(Connection connection) {
    this.connection = connection;
  }

  public Integer getStolikByMac(String macAddress) throws SQLException, IOException {
    String query = "SELECT numer FROM stoliki WHERE mac = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

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
