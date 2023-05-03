package org.psk.client.controller;

public class ClientSocketHandler {
  private final String serverAddress;
  private final int port;

  public ClientSocketHandler(String serverAddress, int port) {
    this.serverAddress = serverAddress;
    this.port = port;
  }

/*
  public String requestTableNumber(String macAddress) {
    String response = "";

    try (Socket socket = new Socket(serverAddress, port)) {
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

      out.println(macAddress);

      response = in.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return response;
  }

 */

}
