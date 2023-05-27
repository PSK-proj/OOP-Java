package org.psk.server.model.database;

import org.psk.shared.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KelnerzyDAO implements DAO {
  private Connection connection;

  @Override
  public void setConnection(Connection connection) {
    this.connection = connection;
  }

  public List<Kelner> getAllKelnerzy() throws SQLException {
    List<Kelner> kelnerzy = new ArrayList<>();
    String query = "SELECT id, imie, nazwisko FROM kelnerzy";
    PreparedStatement statement = connection.prepareStatement(query);
    try (ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        Kelner kelner = new Kelner(resultSet.getInt("id"),
                resultSet.getString("imie"),
                resultSet.getString("nazwisko"));
        kelnerzy.add(kelner);
      }
    }
    return kelnerzy;
  }

  // Tu dodaj metody do zarządzania kelnerami (np. dodawanie, usuwanie)
}
