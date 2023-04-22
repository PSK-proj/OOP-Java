module server {
  requires javafx.controls;
  requires javafx.fxml;

  requires shared;

  opens org.psk.server to javafx.fxml;
  exports org.psk.server;
}
