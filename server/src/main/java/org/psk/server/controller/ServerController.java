package org.psk.server.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.psk.server.Main;
import org.psk.server.util.LogManager;
import org.psk.shared.util.ConfigLoadHelper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable {
  public ServerSocket serverSocket;
  public ServerSocketHandler handler;
  private Main mainApp;

  @FXML
  private ListView<String> historyList;

  private LogManager logManager;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    logManager = LogManager.getInstance(historyList);
    logManager.addLog("Server został uruchomiony");
    new Thread(() -> {
      int port = 12345; // Przykładowy numer portu, który będzie nasłuchiwał serwer
      try {
        port = ConfigLoadHelper.getPort();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      try (ServerSocket serverSocket = new ServerSocket(port)) {
        this.serverSocket = serverSocket;
        System.out.println("Serwer nasłuchuje na porcie: " + port);
        logManager.addLog("Serwer nasłuchuje na porcie: " + port);

        while (!serverSocket.isClosed()) {
          try {
            Socket clientSocket = serverSocket.accept(); // Akceptuj połączenie od klienta
            System.out.println("Połączenie zaakceptowane od: " + clientSocket.getRemoteSocketAddress());
            logManager.addLog("Połączenie zaakceptowane od: " + clientSocket.getRemoteSocketAddress());
            ServerSocketHandler handler = new ServerSocketHandler(clientSocket);
            new Thread(handler).start(); // Uruchom wątek obsługujący połączenie
          } catch (SocketException e) {
            // Obsługa przypadku, gdy serverSocket został zamknięty
            if (serverSocket.isClosed()) {
              System.out.println("Serwer został zamknięty");
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
}
