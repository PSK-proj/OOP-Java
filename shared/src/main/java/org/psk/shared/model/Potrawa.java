package org.psk.shared.model;

public class Potrawa {
  private final int id;
  private final String nazwa;
  private final String kategoria;
  private final double cena;

  public Potrawa(int id, String nazwa, String kategoria, double cena) {
    this.id = id;
    this.nazwa = nazwa;
    this.kategoria = kategoria;
    this.cena = cena;
  }

  public int getId() {
    return id;
  }

  public String getNazwa() {
    return nazwa;
  }

  public String getKategoria() {
    return kategoria;
  }

  public double getCena() {
    return cena;
  }
}
