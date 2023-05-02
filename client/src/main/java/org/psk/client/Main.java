package org.psk.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.psk.client.model.MACAddress;
import org.psk.client.model.SearchForTableNumber;

import java.net.URL;
import java.util.Objects;

public class Main extends Application {

  private static int tableNumber;

  @Override
    public void start(Stage stage) throws Exception {
      SearchForTableNumber.search(); // póki co zostawiamy tak na sztywno
      //Main.testMAC(); // to na później jak już będzie połączenie z serwerem

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/psk/client/view/menuView.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);
      scene.getStylesheets().add(Main.loadCSS());
      stage.setScene(scene);
      stage.setTitle("La Dolce Vita - Menu");
      stage.getIcons().add(loadLogo());
      stage.show();
  }

  public static String loadCSS() {
    URL cssURL = Main.class.getResource("/org/psk/client/main.css");
    return Objects.requireNonNull(cssURL).toExternalForm();
  }

  public static Image loadLogo() {
    URL logoURL = Main.class.getResource("/org/psk/client/images/logo.png");
    return new Image(Objects.requireNonNull(logoURL).toExternalForm());
  }

  private static void testMAC() {
    String serverAddress = "127.0.0.1"; // adres IP serwera
    int serverPort = 12345; // port serwera

    String macAddress = MACAddress.getMACAddress(serverAddress, serverPort);
    System.out.println("Adres MAC: " + macAddress);
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
