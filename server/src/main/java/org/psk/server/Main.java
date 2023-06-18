package org.psk.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.psk.server.controller.ServerController;

import java.net.URL;
import java.util.Objects;

public class Main extends Application {
  private ServerController serverController;
  @Override
    public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/psk/server/view/serverView.fxml"));
    Parent root = loader.load();
    serverController = loader.getController();
    serverController.setMainApp(this);
    Scene scene = new Scene(root);
    scene.getStylesheets().add(Main.loadCSS());
    stage.setScene(scene);
    stage.setTitle("La Dolce Vita - Serwer");
    stage.show();
  }

  /**
   * Metoda zatrzymująca serwer.
   */
  @Override
  public void stop() {
    if (serverController != null) {
      serverController.stopServer();
    }
  }
  /**
   * Metoda ładująca plik CSS.
   * @return CSS przygotowany do użycia w widoku.
   */
  public static String loadCSS() {
    URL cssURL = Main.class.getResource("/org/psk/server/main.css");
    return Objects.requireNonNull(cssURL).toExternalForm();
  }

  /**
   * Setter kontrolera serwera.
   * @param serverController
   */
  public void setServerController(ServerController serverController) {
    this.serverController = serverController;
  }
  public static void main(String[] args) {
    launch(args);
  }
}
