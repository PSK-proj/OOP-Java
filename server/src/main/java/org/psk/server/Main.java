package org.psk.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class Main extends Application {

  @Override
    public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/psk/server/view/serverView.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    scene.getStylesheets().add(Main.loadCSS());
    stage.setScene(scene);
    stage.setTitle("La Dolce Vita - Serwer");
    stage.show();
  }

  public static String loadCSS() {
    URL cssURL = Main.class.getResource("/org/psk/server/main.css");
    return Objects.requireNonNull(cssURL).toExternalForm();
  }


  public static void main(String[] args) {
    launch(args);
  }
}
