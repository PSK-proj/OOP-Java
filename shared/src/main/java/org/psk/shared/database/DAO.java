package org.psk.shared.database;

import java.sql.Connection;

public interface DAO {
  void setConnection(Connection connection);
}
