module client {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;

  requires shared;

  opens org.psk.client to javafx.fxml;
  exports org.psk.client;
  opens org.psk.client.controller to javafx.fxml;
  exports org.psk.client.controller;
}
