package org.psk.client.model;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Klasa odpowiedzialna za odczytywanie adresu MAC urządzenia.
 */
public class MacAddressFinder {
  /**
   * Metoda odczytująca MAC interfejsu, którym urządzenie łączy się z serwerem.
   * @param clientSocket Socket którym urządzenie łączy się z serwerem.
   * @return Adres MAC.
   */
  public static String getMacAddress(Socket clientSocket) {
    try {
      InetAddress localAddress = clientSocket.getLocalAddress();
      Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
      while (networkInterfaces.hasMoreElements()) {
        NetworkInterface networkInterface = networkInterfaces.nextElement();
        if (networkInterface.isUp() && !networkInterface.isLoopback()) {
          Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
          while (inetAddresses.hasMoreElements()) {
            InetAddress inetAddress = inetAddresses.nextElement();
            if (inetAddress.isSiteLocalAddress() && inetAddress.getHostAddress().equals(localAddress.getHostAddress())) {
              byte[] mac = networkInterface.getHardwareAddress();
              if (mac != null) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                  sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
                return sb.toString();
              }
            }
          }
        }
      }
    } catch (SocketException e) {
      e.printStackTrace();
    }
    return null;
  }
}
