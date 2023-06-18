package org.psk.client;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.psk.client.model.ConnectionManager;
import org.psk.client.model.database.DatabaseConnection;

import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;

public class Main extends Application {

  private static final IntegerProperty tableNumber = new SimpleIntegerProperty(0);
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
      socket = ConnectionManager.getSocket();
      if (socket != null && !socket.isClosed()) {
        ConnectionManager.closeResources();
        try {
          DatabaseConnection.closeConnection();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }
    });

  }

  /**
   * Metoda ładująca plik CSS.
   * @return CSS przygotowany do użycia w widoku.
   */
  public static String loadCSS() {
    URL cssURL = Main.class.getResource("/org/psk/client/main.css");
    return Objects.requireNonNull(cssURL).toExternalForm();
  }

  /**
   * Metoda ładująca logo programu.
   * @return logo przygotowane do użycia.
   */
  public static Image loadLogo() {
    URL logoURL = Main.class.getResource("/org/psk/client/images/logo.png");
    return new Image(Objects.requireNonNull(logoURL).toExternalForm());
  }

  /**
   * Getter numeru stolika.
   * @return numer stolika.
   */
  public static int getTableNumber() {
    return tableNumber.get();
  }

  /**
   * Metoda potrzebna do prawidłowego wyświetlania numeru stolika.
   * @return tableNumber.
   */
  public static IntegerProperty tableNumberProperty() {
    return tableNumber;
  }

  /**
   * Setter numeru stolika.
   * @param tableNumber Numer stolika.
   */
  public static void setTableNumber(int tableNumber) {
    Main.tableNumber.set(tableNumber);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
