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

import java.io.IOException;

public class MenuController {
  @FXML
  private Label tableNumberLabel;

  public void initialize() {
    tableNumberLabel.setText(String.valueOf(Main.getTableNumber()));
  }
  public void showSummaryView(ActionEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/psk/client/view/summaryView.fxml"));
      Parent summaryView = loader.load();

      summaryView.getStylesheets().add(Main.loadCSS());

      Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
      stage.setTitle("La Dolce Vita - Podsumowanie");
      stage.setScene(new Scene(summaryView));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
