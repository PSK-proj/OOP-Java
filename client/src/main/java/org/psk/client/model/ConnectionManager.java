package org.psk.client.model;

import java.net.Socket;

public class ConnectionManager {
  private static Socket socket;

  public static Socket getSocket() {
    return socket;
  }

  public static void setSocket(Socket socket) {
    ConnectionManager.socket = socket;
  }
}

