package org.psk.client.model.database;

import org.psk.shared.database.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Klasa obsługująca wyszukanie numeru stolika w bazie.
 */
public class StolikiDAO implements DAO {
  private Connection connection;

  /**
   * Nawiązanie połączenia.
   * @param connection Uchwyt na połączenie do bazy.
   */
  @Override
  public void setConnection(Connection connection) {
    this.connection = connection;
  }

  /**
   * Wyszukiwanie stolika na podstawie adresu MAC.
   * @param macAddress adres MAC urządzenia.
   * @return Odczytany numer stolika.
   * @throws SQLException
   * @throws IOException
   */
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
