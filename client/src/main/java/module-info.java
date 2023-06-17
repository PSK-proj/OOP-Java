module client {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;

  requires shared;

  requires java.sql;
  requires org.apache.logging.log4j;

  opens org.psk.client to javafx.fxml;
  exports org.psk.client;
  opens org.psk.client.controller to javafx.fxml;
  exports org.psk.client.controller;
  opens org.psk.client.util to javafx.fxml;
  exports org.psk.client.util;
}
