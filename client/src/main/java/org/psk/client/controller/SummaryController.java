package org.psk.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.psk.client.Main;

import java.io.IOException;

public class SummaryController {

  public void returnToMenu(ActionEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/psk/client/view/menuView.fxml"));
      Parent menuView = loader.load();

      menuView.getStylesheets().add(Main.loadCSS());

      Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
      stage.setScene(new Scene(menuView));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void submitOrder(ActionEvent event) {
    // Logika wysyłania zamówienia do serwera
    // ...

    // Przejście do widoku potwierdzenia
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/psk/client/view/confirmationView.fxml"));
      Parent confirmationView = loader.load();

      confirmationView.getStylesheets().add(Main.loadCSS());

      Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
      stage.setScene(new Scene(confirmationView));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
