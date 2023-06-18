package org.psk.client.util;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.psk.client.Main;

import java.io.IOException;

/**
 * Klasa pomocnicza wyświetlająca widok menu.
 */
public class MenuHelper {
  public static void showMenu(ActionEvent event) {
    Platform.runLater(() -> {
      try {
        FXMLLoader loader = new FXMLLoader(MenuHelper.class.getResource("/org/psk/client/view/menuView.fxml"));
        Parent menuView = loader.load();

        menuView.getStylesheets().add(Main.loadCSS());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("La Dolce Vita - Menu");
        stage.setScene(new Scene(menuView));
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  public static void showMenu(Node node) throws IOException {
    Platform.runLater(() -> {
      try {
        FXMLLoader loader = new FXMLLoader(MenuHelper.class.getResource("/org/psk/client/view/menuView.fxml"));
        Parent menuView = loader.load();

        menuView.getStylesheets().add(Main.loadCSS());

        Stage stage = (Stage) node.getScene().getWindow();
        stage.setTitle("La Dolce Vita - Menu");
        stage.setScene(new Scene(menuView));
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }
}
