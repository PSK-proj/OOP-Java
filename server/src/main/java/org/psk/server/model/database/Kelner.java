package org.psk.server.model.database;

/**
 * Klasa kelnera.
 */
public class Kelner {
  private final int id;
  private final String imie;
  private final String nazwisko;

  public Kelner(int id, String imie, String nazwisko) {
    this.id = id;
    this.imie = imie;
    this.nazwisko = nazwisko;
  }

  public int getId() {
    return id;
  }

  public String getImie() {
    return imie;
  }

  public String getNazwisko() {
    return nazwisko;
  }
}
