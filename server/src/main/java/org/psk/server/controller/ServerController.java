package org.psk.server.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import org.apache.logging.log4j.Logger;
import org.psk.server.Main;
import org.psk.server.model.database.DatabaseConnection;
import org.psk.server.model.database.Kelner;
import org.psk.server.model.database.KelnerzyDAO;
import org.psk.server.util.LogManager;
import org.psk.shared.util.ConfigLoadHelper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Kontroler serwera.
 */
public class ServerController implements Initializable {
  private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(ServerController.class);
  public ServerSocket serverSocket;
  public ServerSocketHandler handler;
  private Main mainApp;

  @FXML
  private Tab kelnerTab1;
  @FXML
  private Tab kelnerTab2;
  @FXML
  private Tab kelnerTab3;

  @FXML
  private ListView<String> historyList;

  private LogManager logManager;

  /**
   * Metoda inicjalizacji.
   * @param location
   * @param resources
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    logManager = LogManager.getInstance(historyList);
    logger.info("Server został uruchomiony");
    logManager.addLog("Server został uruchomiony");

    setTabNames();

    new Thread(() -> {
      int port = 12345; // Przykładowy numer portu, który będzie nasłuchiwał serwer
      try {
        port = ConfigLoadHelper.getPort();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      try (ServerSocket serverSocket = new ServerSocket(port)) {
        this.serverSocket = serverSocket;
        logger.debug("Serwer nasłuchuje na porcie: " + port);
        logManager.addLog("Serwer nasłuchuje na porcie: " + port);

        while (!serverSocket.isClosed()) {
          try {
            Socket clientSocket = serverSocket.accept(); // Akceptuj połączenie od klienta
            logger.debug("Połączenie zaakceptowane od: " + clientSocket.getRemoteSocketAddress());
            logManager.addLog("Połączenie zaakceptowane od: " + clientSocket.getRemoteSocketAddress());
            ServerSocketHandler handler = new ServerSocketHandler(clientSocket);
            new Thread(handler).start(); // Uruchom wątek obsługujący połączenie
          } catch (SocketException e) {
            // Obsługa przypadku, gdy serverSocket został zamknięty
            if (serverSocket.isClosed()) {
              logger.info("Serwer został zamknięty");
            } else {
              e.printStackTrace();
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();
  }

  public void setMainApp(Main mainApp) {
    this.mainApp = mainApp;
  }

  /**
   * Metoda zatrzymująca serwer.
   */
  public void stopServer() {
    if (handler != null) {
      handler.closeConnection();
    }
    if (serverSocket != null) {
      try {
        serverSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Metoda przypisująca zakładkom kolejnych kelnerów.
   */
  private void setTabNames() {
    try {
      KelnerzyDAO kelnerzyDAO = new KelnerzyDAO();
      kelnerzyDAO.setConnection(DatabaseConnection.getConnection());
      List<Kelner> kelnerzy = kelnerzyDAO.getAllKelnerzy();

      // Ustalamy tytuły dla zakładek
      if(kelnerzy.size() > 0) {
        kelnerTab1.setText("Kelner " + kelnerzy.get(0).getId() + ": " + kelnerzy.get(0).getImie() + " " + kelnerzy.get(0).getNazwisko());
      }
      if(kelnerzy.size() > 1) {
        kelnerTab2.setText("Kelner " + kelnerzy.get(1).getId() + ": " + kelnerzy.get(1).getImie() + " " + kelnerzy.get(1).getNazwisko());
      }
      if(kelnerzy.size() > 2) {
        kelnerTab3.setText("Kelner " + kelnerzy.get(2).getId() + ": " + kelnerzy.get(2).getImie() + " " + kelnerzy.get(2).getNazwisko());
      }
      logger.info("Odczytano kelnerów z bazy");
      logManager.addLog("Odczytano kelnerów z bazy");
    } catch (SQLException | IOException e) {
      e.printStackTrace();
    }
  }
}
