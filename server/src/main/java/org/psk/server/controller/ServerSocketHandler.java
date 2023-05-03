package org.psk.server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerSocketHandler implements Runnable {
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;

  public ServerSocketHandler(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    try {
      // Inicjalizacja strumieni wejścia i wyjścia
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);

      while (true) {
        if (socket.isClosed()) {
          break;
        }

        String message = in.readLine();

        if (message == null) {
          break;
        }

        // Obsługa wiadomości od klienta
        switch (message) {
          case "REQUEST_TABLE_NUMBER":
            // Przetwarzanie żądania przypisania numeru stolika
            break;
          case "SEND_ORDER":
            // Przetwarzanie wysyłania zamówienia
            break;
          // ...
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
  }
  public void closeConnection() {
    try {
      // Zamknięcie strumieni wejścia i wyjścia oraz gniazda
      if (in != null) {
        in.close();
      }
      if (out != null) {
        out.close();
      }
      if (socket != null) {
        socket.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
