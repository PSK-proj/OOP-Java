package org.psk.client.util;

import javafx.collections.ListChangeListener;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.psk.shared.database.MenuDAO;
import org.psk.shared.model.Potrawa;
import org.psk.shared.model.PotrawaWrapper;

import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Klasa odpowiedzialna za wybór potraw i zapisanie ich do listy.
 */
public class MenuManager {

  final LinkedHashSet<PotrawaWrapper> selectedNapoje = new LinkedHashSet<>();
  final LinkedHashSet<PotrawaWrapper> selectedDaniaGlowne = new LinkedHashSet<>();
  final LinkedHashSet<PotrawaWrapper> selectedDesery = new LinkedHashSet<>();


  public void loadMenuData(MenuDAO menuDAO, ListView<PotrawaWrapper> listViewNapoje, ListView<PotrawaWrapper> listViewDaniaGlowne, ListView<PotrawaWrapper> listViewDesery) {
    try {
      int numerNapoju = 1;
      int numerDaniaGlowne = 1;
      int numerDeseru = 1;

      List<Potrawa> potrawy = menuDAO.getAllPotrawy();
      for (Potrawa potrawa : potrawy) {
        switch (potrawa.getKategoria()) {
          case "napoje":
            listViewNapoje.getItems().add(new PotrawaWrapper(potrawa, numerNapoju++));
            break;
          case "dania główne":
            listViewDaniaGlowne.getItems().add(new PotrawaWrapper(potrawa, numerDaniaGlowne++));
            break;
          case "desery":
            listViewDesery.getItems().add(new PotrawaWrapper(potrawa, numerDeseru++));
            break;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void bindSelection(ListView<PotrawaWrapper> listViewNapoje, ListView<PotrawaWrapper> listViewDaniaGlowne, ListView<PotrawaWrapper> listViewDesery) {
    listViewNapoje.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    listViewDaniaGlowne.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    listViewDesery.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    listViewNapoje.getSelectionModel().getSelectedItems().addListener((ListChangeListener<PotrawaWrapper>) change -> {
      while (change.next()) {
        if (change.wasAdded()) {
          selectedNapoje.addAll(change.getAddedSubList());
        } else if (change.wasRemoved()) {
          selectedNapoje.removeAll(change.getRemoved());
        }
      }
    });

    listViewDaniaGlowne.getSelectionModel().getSelectedItems().addListener((ListChangeListener<PotrawaWrapper>) change -> {
      while (change.next()) {
        if (change.wasAdded()) {
          selectedDaniaGlowne.addAll(change.getAddedSubList());
        } else if (change.wasRemoved()) {
          selectedDaniaGlowne.removeAll(change.getRemoved());
        }
      }
    });

    listViewDesery.getSelectionModel().getSelectedItems().addListener((ListChangeListener<PotrawaWrapper>) change -> {
      while (change.next()) {
        if (change.wasAdded()) {
          selectedDesery.addAll(change.getAddedSubList());
        } else if (change.wasRemoved()) {
          selectedDesery.removeAll(change.getRemoved());
        }
      }
    });

    // Napoje
    listViewNapoje.setCellFactory(lv -> {
      ListCell<PotrawaWrapper> cell = new ListCell<>() {
        @Override
        protected void updateItem(PotrawaWrapper item, boolean empty) {
          super.updateItem(item, empty);
          if (empty) {
            setText(null);
          } else {
            setText(item.toString());
          }
        }
      };
      cell.itemProperty().addListener((obs, oldItem, newItem) -> {
        if (newItem == null) {
          cell.setStyle("");
        } else if (selectedNapoje.contains(newItem)) {
          cell.setStyle("-fx-control-inner-background: #ffa3a3;");
        } else {
          cell.setStyle("");
        }
      });
      cell.setOnMouseClicked(event -> {
        PotrawaWrapper item = cell.getItem();
        if (item != null) {
          if (!selectedNapoje.remove(item)) {
            selectedNapoje.add(item);
            cell.setStyle("-fx-control-inner-background: #ffa3a3;");
          } else {
            cell.setStyle("");
          }
        }
      });
      return cell ;
    });


    // Dania główne
    listViewDaniaGlowne.setCellFactory(lv -> {
      ListCell<PotrawaWrapper> cell = new ListCell<>() {
        @Override
        protected void updateItem(PotrawaWrapper item, boolean empty) {
          super.updateItem(item, empty);
          if (empty) {
            setText(null);
          } else {
            setText(item.toString());
          }
        }
      };
      cell.itemProperty().addListener((obs, oldItem, newItem) -> {
        if (newItem == null) {
          cell.setStyle("");
        } else if (selectedDaniaGlowne.contains(newItem)) {
          cell.setStyle("-fx-control-inner-background: #ffa3a3;");
        } else {
          cell.setStyle("");
        }
      });
      cell.setOnMouseClicked(event -> {
        PotrawaWrapper item = cell.getItem();
        if (item != null) {
          if (!selectedDaniaGlowne.remove(item)) {
            selectedDaniaGlowne.add(item);
            cell.setStyle("-fx-control-inner-background: #ffa3a3;");
          } else {
            cell.setStyle("");
          }
        }
      });
      return cell ;
    });


    // Desery
    listViewDesery.setCellFactory(lv -> {
      ListCell<PotrawaWrapper> cell = new ListCell<>() {
        @Override
        protected void updateItem(PotrawaWrapper item, boolean empty) {
          super.updateItem(item, empty);
          if (empty) {
            setText(null);
          } else {
            setText(item.toString());
          }
        }
      };
      cell.itemProperty().addListener((obs, oldItem, newItem) -> {
        if (newItem == null) {
          cell.setStyle("");
        } else if (selectedDesery.contains(newItem)) {
          cell.setStyle("-fx-control-inner-background: #ffa3a3;");
        } else {
          cell.setStyle("");
        }
      });
      cell.setOnMouseClicked(event -> {
        PotrawaWrapper item = cell.getItem();
        if (item != null) {
          if (!selectedDesery.remove(item)) {
            selectedDesery.add(item);
            cell.setStyle("-fx-control-inner-background: #ffa3a3;");
          } else {
            cell.setStyle("");
          }
        }
      });
      return cell ;
    });
  }

  /**
   * Metoda zwracająca wybrane potrawy ze wszystkich kategorii (całe zamówienie).
   * @return Zbiór wybranych potraw.
   */
  public LinkedHashSet<PotrawaWrapper> getSelectedPotrawy() {
    LinkedHashSet<PotrawaWrapper> selectedPotrawy = new LinkedHashSet<>();
    selectedPotrawy.addAll(selectedNapoje);
    selectedPotrawy.addAll(selectedDaniaGlowne);
    selectedPotrawy.addAll(selectedDesery);
    return selectedPotrawy;
  }
}
