package org.psk.client.controller;

public class ClientSocketHandler {
  private final String serverAddress;
  private final int port;

  public ClientSocketHandler(String serverAddress, int port) {
    this.serverAddress = serverAddress;
    this.port = port;
  }
}
