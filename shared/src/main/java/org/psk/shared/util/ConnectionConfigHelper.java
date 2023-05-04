package org.psk.shared.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionConfigHelper {
  public static String GetIP() throws IOException {
    Properties properties = new Properties();
    try (InputStream inputStream = ConnectionConfigHelper.class.getResourceAsStream("/org/psk/shared/config.properties")) {
      if (inputStream == null) {
        throw new IOException("Nie można znaleźć pliku config.properties");
      }
      properties.load(inputStream);
    }
    return properties.getProperty("server.ip");
  }

  public static int GetPort() throws IOException {
    Properties properties = new Properties();
    try (InputStream inputStream = ConnectionConfigHelper.class.getResourceAsStream("/org/psk/shared/config.properties")) {
      if (inputStream == null) {
        throw new IOException("Nie można znaleźć pliku config.properties");
      }
      properties.load(inputStream);
    }
    return Integer.parseInt(properties.getProperty("server.port"));
  }
}
