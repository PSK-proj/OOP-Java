module shared {
  requires java.sql;
  exports org.psk.shared.util;
  opens org.psk.shared.util to client, server;
  exports org.psk.shared.database;
  exports org.psk.shared.model;
}