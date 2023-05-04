package org.psk.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.psk.client.Main;
import org.psk.client.util.MenuHelper;

public class ConfirmationController {
  @FXML
  private Label tableNumberLabel;
  public void initialize() {
    tableNumberLabel.setText(String.valueOf(Main.getTableNumber()));
  }
  public void returnToMenu(ActionEvent event) {
    MenuHelper.showMenu(event);
  }
}
