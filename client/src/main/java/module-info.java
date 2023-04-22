module client {
  requires javafx.controls;
  requires javafx.fxml;

  requires shared;

  opens org.psk.client to javafx.fxml;
  exports org.psk.client;
}
