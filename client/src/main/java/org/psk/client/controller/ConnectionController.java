package org.psk.client.controller;

import javafx.fxml.Initializable;
import org.psk.client.model.ConnectionManager;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ConnectionController implements Initializable {
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    new Thread(() -> {
      try {
        String serverAddress = "192.168.0.5"; // Adres IP serwera
        int port = 12345; // Przykładowy numer portu serwera

        Socket socket = new Socket(serverAddress, port);
        ConnectionManager.setSocket(socket);
        System.out.println("Połączono z serwerem");

        // Przełącz na kolejny widok (np. menuView) po pomyślnym połączeniu z serwerem
        // ...

      } catch (IOException e) {
        e.printStackTrace();
        // Obsłuż sytuację, gdy nie można połączyć się z serwerem
        // ...
      }
    }).start();
  }
}