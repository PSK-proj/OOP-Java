package org.psk.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.psk.client.model.ConnectionManager;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.Objects;

public class Main extends Application {

  private static int tableNumber;
  private Socket socket;

  @Override
    public void start(Stage stage) throws Exception {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/psk/client/view/connectionView.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);
      scene.getStylesheets().add(Main.loadCSS());
      stage.setScene(scene);
      stage.setTitle("La Dolce Vita - Menu");
      stage.getIcons().add(loadLogo());
      stage.show();
      stage.setOnCloseRequest(event -> {
      try {
        socket = ConnectionManager.getSocket();
        if (socket != null && !socket.isClosed()) {
          socket.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  public static String loadCSS() {
    URL cssURL = Main.class.getResource("/org/psk/client/main.css");
    return Objects.requireNonNull(cssURL).toExternalForm();
  }

  public static Image loadLogo() {
    URL logoURL = Main.class.getResource("/org/psk/client/images/logo.png");
    return new Image(Objects.requireNonNull(logoURL).toExternalForm());
  }

  public static void setTableNumber(int number) {
    tableNumber = number;
  }
  public static int getTableNumber() {
    return tableNumber;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
