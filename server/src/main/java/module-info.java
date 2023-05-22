module server {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;

  requires shared;

  requires java.sql;

  opens org.psk.server to javafx.fxml;
  exports org.psk.server;
  opens org.psk.server.controller to javafx.fxml;
  exports org.psk.server.controller;
}
