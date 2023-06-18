package org.psk.server.model.database;

import org.psk.shared.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Klasa obsługująca stoliki w bazie.
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
   * Metoda przypisująca numer stolika do adresu MAC.
   * @param macAddress Adres MAC
   * @param tableNumber Numer stolika
   * @throws SQLException
   */
  public void assignTable(String macAddress, int tableNumber) throws SQLException {
    String query = "INSERT INTO `stoliki` (`numer`, `mac`) VALUES (?, ?)";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setInt(1, tableNumber);
    statement.setString(2, macAddress);
    statement.executeUpdate();
  }

  /**
   * Metoda pobierająca ostatni przypisany numer stolika.
   * @return Numer ostatniego stolika.
   * @throws SQLException
   */
  public Integer getLastTableNumber() throws SQLException {
    String query = "SELECT numer FROM stoliki ORDER BY numer DESC LIMIT 1";
    PreparedStatement statement = connection.prepareStatement(query);
    try (ResultSet resultSet = statement.executeQuery()) {
      if (resultSet.next()) {
        return resultSet.getInt("numer");
      } else {
        return 0;
      }
    }
  }
}
