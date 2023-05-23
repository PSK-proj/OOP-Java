package org.psk.server.util;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class LogManager {
  private static LogManager instance = null;
  private final ListView<String> listView;
  private final ObservableList<String> logList;

  private LogManager(ListView<String> listView) {
    this.listView = listView;
    this.logList = FXCollections.observableArrayList();
    this.listView.setItems(logList);
  }

  public static LogManager getInstance(ListView<String> listView) {
    if(instance == null) {
      instance = new LogManager(listView);
    }
    return instance;
  }

  public static LogManager getInstance() {
    if (instance == null) {
      throw new IllegalStateException("LogManager not initialized");
    }
    return instance;
  }

  public void addLog(String log) {
    Platform.runLater(() -> logList.add(log));
  }
}
