package org.psk.client.model;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

public class MACAddress {

  public static String getMACAddress(String serverAddress, int serverPort) {
    try {
      Socket socket = new Socket();
      socket.connect(new InetSocketAddress(serverAddress, serverPort));
      InetAddress localAddress = socket.getLocalAddress();
      socket.close();

      Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

      while (networkInterfaces.hasMoreElements()) {
        NetworkInterface network = networkInterfaces.nextElement();
        InetAddress inetAddress = network.getInetAddresses().nextElement();

        if (inetAddress.equals(localAddress)) {
          byte[] macBytes = network.getHardwareAddress();

          if (macBytes != null) {
            StringBuilder macAddress = new StringBuilder();
            for (int i = 0; i < macBytes.length; i++) {
              macAddress.append(String.format("%02X%s", macBytes[i], (i < macBytes.length - 1) ? "-" : ""));
            }
            return macAddress.toString();
          }
        }
      }
    } catch (SocketException | UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }
}
