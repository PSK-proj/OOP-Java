package org.psk.shared.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Klasa ładująca dane z pliku konfiguracyjnego.
 */
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

  /**
   * Metoda zwracająca numer portu.
   * @return Numer portu.
   * @throws IOException
   */
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

  /**
   * Metoda zwracająca URL bazy danych.
   * @return URL bazy danych.
   * @throws IOException
   */
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

  /**
   * Metoda zwracająca nazwę użytkownika do konta klienta.
   * @return Nazwa użytkownika dla klienta w bazie.
   * @throws IOException
   */
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

  /**
   * Metoda zwracająca hasło użytkownika do konta klienta.
   * @return Hasło użytkownika dla klienta w bazie.
   * @throws IOException
   */
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

  /**
   * Metoda zwracająca nazwę użytkownika do konta serwera.
   * @return Nazwa użytkownika dla serwera w bazie.
   * @throws IOException
   */
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

  /**
   * Metoda zwracająca hasło użytkownika do konta serwera.
   * @return Hasło użytkownika dla serwera w bazie.
   * @throws IOException
   */
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
