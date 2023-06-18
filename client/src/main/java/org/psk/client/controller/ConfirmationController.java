package org.psk.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.psk.client.Main;
import org.psk.client.util.MenuHelper;

/**
 * Kontroler widoku potwierdzenia zamówienia.
 */
public class ConfirmationController {
  @FXML
  Label tableNumberLabel = new Label();

  /**
   * Metoda inicjalizacji.
   */
  public void initialize() {
    tableNumberLabel.textProperty().bind(Main.tableNumberProperty().asString());
  }

  /**
   * Metoda powrotu do menu.
   * @param event
   */
  public void returnToMenu(ActionEvent event) {
    MenuHelper.showMenu(event);
  }
}
