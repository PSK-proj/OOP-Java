package org.psk.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.psk.client.Main;
import org.psk.client.model.database.DatabaseConnection;
import org.psk.client.util.MenuManager;
import org.psk.shared.database.MenuDAO;
import org.psk.shared.model.PotrawaWrapper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashSet;

public class MenuController {

  private static final Logger logger = LogManager.getLogger(MenuController.class);

  @FXML
  Label tableNumberLabel = new Label();
  @FXML
  private ListView<PotrawaWrapper> listViewNapoje;
  @FXML
  private ListView<PotrawaWrapper> listViewDaniaGlowne;
  @FXML
  private ListView<PotrawaWrapper> listViewDesery;


  private MenuManager menuManager = new MenuManager();

  public void initialize() throws SQLException, IOException {
    tableNumberLabel.textProperty().bind(Main.tableNumberProperty().asString());

    MenuDAO menuDAO = new MenuDAO();
    menuDAO.setConnection(DatabaseConnection.getConnection());

    menuManager.loadMenuData(menuDAO, listViewNapoje, listViewDaniaGlowne, listViewDesery);
    menuManager.bindSelection(listViewNapoje, listViewDaniaGlowne, listViewDesery);
  }

  public LinkedHashSet<PotrawaWrapper> getSelectedPotrawy() {
    return menuManager.getSelectedPotrawy();
  }
  public void showSummaryView(ActionEvent event) {
    logger.debug("Zamówienie: " + menuManager.getSelectedPotrawy());
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
