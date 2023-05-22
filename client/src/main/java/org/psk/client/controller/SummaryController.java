package org.psk.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.psk.client.Main;
import org.psk.client.util.MenuHelper;

import java.io.IOException;

public class SummaryController {
  @FXML
  Label tableNumberLabel = new Label();
  public void initialize() {
    tableNumberLabel.textProperty().bind(Main.tableNumberProperty().asString());
  }

  public void returnToMenu(ActionEvent event) {
    MenuHelper.showMenu(event);
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
      stage.setTitle("La Dolce Vita - Potwierdzenie");
      stage.setScene(new Scene(confirmationView));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
