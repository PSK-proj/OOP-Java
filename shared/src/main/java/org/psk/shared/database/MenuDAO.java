package org.psk.shared.database;

import org.psk.shared.model.Potrawa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa obsługująca pobieranie potraw z bazy danych.
 */
public class MenuDAO  implements DAO{
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
   * Pobieranie wszystkich potraw z bazy.
   * @return Lista potraw.
   * @throws SQLException
   */
  public List<Potrawa> getAllPotrawy() throws SQLException {
    List<Potrawa> menu = new ArrayList<>();
    String query = "SELECT id, nazwa, kategoria, cena FROM menu";
    PreparedStatement statement = connection.prepareStatement(query);
    try (ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        Potrawa potrawa = new Potrawa(resultSet.getInt("id"),
                resultSet.getString("nazwa"),
                resultSet.getString("kategoria"),
                resultSet.getDouble("cena"));
        menu.add(potrawa);
      }
    }
    return menu;
  }
}