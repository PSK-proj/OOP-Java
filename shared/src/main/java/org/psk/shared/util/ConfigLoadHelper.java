package org.psk.shared.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoadHelper {
  private static final String configPath = "/org/psk/shared/config.properties";
  public static String getIP() throws IOException {
    Properties properties = new Properties();
    try (InputStream inputStream = ConfigLoadHelper.class.getResourceAsStream(configPath)) {
      if (inputStream == null) {
        throw new IOException("Nie można znaleźć pliku config.properties");
      }
      properties.load(inputStream);
    }
    return properties.getProperty("server.ip");
  }

  public static int getPort() throws IOException {
    Properties properties = new Properties();
    try (InputStream inputStream = ConfigLoadHelper.class.getResourceAsStream(configPath)) {
      if (inputStream == null) {
        throw new IOException("Nie można znaleźć pliku config.properties");
      }
      properties.load(inputStream);
    }
    return Integer.parseInt(properties.getProperty("server.port"));
  }
  public static String getDbUrl() throws IOException {
    Properties properties = new Properties();
    try (InputStream inputStream = ConfigLoadHelper.class.getResourceAsStream(configPath)) {
      if (inputStream == null) {
        throw new IOException("Nie można znaleźć pliku config.properties");
      }
      properties.load(inputStream);
    }

    return properties.getProperty("db.url");
  }

  public static String getClientDbUser() throws IOException {
    Properties properties = new Properties();
    try (InputStream inputStream = ConfigLoadHelper.class.getResourceAsStream(configPath)) {
      if (inputStream == null) {
        throw new IOException("Nie można znaleźć pliku config.properties");
      }
      properties.load(inputStream);
    }

    return properties.getProperty("dbc.user");
  }

  public static String getClientDbPass() throws IOException {
    Properties properties = new Properties();
    try (InputStream inputStream = ConfigLoadHelper.class.getResourceAsStream(configPath)) {
      if (inputStream == null) {
        throw new IOException("Nie można znaleźć pliku config.properties");
      }
      properties.load(inputStream);
    }

    return properties.getProperty("dbc.password");
  }

  public static String getServerDbUser() throws IOException {
    Properties properties = new Properties();
    try (InputStream inputStream = ConfigLoadHelper.class.getResourceAsStream(configPath)) {
      if (inputStream == null) {
        throw new IOException("Nie można znaleźć pliku config.properties");
      }
      properties.load(inputStream);
    }

    return properties.getProperty("dbs.user");
  }

  public static String getServerDbPass() throws IOException {
    Properties properties = new Properties();
    try (InputStream inputStream = ConfigLoadHelper.class.getResourceAsStream(configPath)) {
      if (inputStream == null) {
        throw new IOException("Nie można znaleźć pliku config.properties");
      }
      properties.load(inputStream);
    }

    return properties.getProperty("dbs.password");
  }
}
