package org.psk.client.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.psk.client.model.ConnectionManager;
import org.psk.client.model.SearchForTableNumber;
import org.psk.client.util.MenuHelper;
import org.psk.shared.util.ConfigLoadHelper;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroler obsługujący połączenie z serwerem.
 */
public class ConnectionController implements Initializable {

  private static final Logger logger = LogManager.getLogger(ConnectionController.class);

  @FXML
  private Label info;

  /**
   * Metoda inicjalizacji.
   * @param location
   * @param resources
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    new Thread(() -> {
      try {
        String serverAddress = ConfigLoadHelper.getIP(); // Adres IP serwera
        int port = ConfigLoadHelper.getPort(); // Przykładowy numer portu serwera

        Socket socket = new Socket(serverAddress, port);
        ConnectionManager.setSocket(socket);

        logger.info("Połączono z serwerem");

        // Przełącz na kolejny widok (np. menuView) po pomyślnym połączeniu z serwerem
        SearchForTableNumber.search();
        try {
          MenuHelper.showMenu(info);
        } catch (IOException e) {
          e.printStackTrace();
        }

      } catch (IOException e) {
        e.printStackTrace();
        // Obsłuż sytuację, gdy nie można połączyć się z serwerem
        Platform.runLater(() -> info.setText("Server unreachable!"));
        logger.info("Nie odnaleziono serwera!");
      }
    }).start();
  }
}