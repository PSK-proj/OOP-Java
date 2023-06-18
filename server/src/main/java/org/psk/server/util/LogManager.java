package org.psk.server.util;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 * Klasa służąca wyświetlaniu zdarzeń w aplikacji serwera.
 */
public class LogManager {
  private static LogManager instance = null;
  private final ListView<String> listView;
  private final ObservableList<String> logList;

  /**
   * Konstruktor klasy.
   * @param listView Element widoku.
   */
  private LogManager(ListView<String> listView) {
    this.listView = listView;
    this.logList = FXCollections.observableArrayList();
    this.listView.setItems(logList);
  }

  /**
   * Metoda odpowiedzialna za pobranie instancji elementu.
   * @param listView Element widoku.
   * @return Instancja elementu.
   */
  public static LogManager getInstance(ListView<String> listView) {
    if(instance == null) {
      instance = new LogManager(listView);
    }
    return instance;
  }

  /**
   * Przeciążona metoda odpowiedzialna za pobranie instancji elementu.
   * @return Instancja elementu.
   */
  public static LogManager getInstance() {
    if (instance == null) {
      throw new IllegalStateException("LogManager not initialized");
    }
    return instance;
  }

  /**
   * Metoda dodająca nową pozycję do elementu listy.
   * @param log Zawartość do wstawienia.
   */
  public void addLog(String log) {
    Platform.runLater(() -> logList.add(log));
  }
}
