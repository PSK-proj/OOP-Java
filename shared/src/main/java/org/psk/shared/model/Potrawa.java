package org.psk.shared.model;

/**
 * Klasa potrawy.
 */
public class Potrawa {
  private final int id;
  private final String nazwa;
  private final String kategoria;
  private final double cena;

  /**
   * Konstruktor klasy.
   * @param id
   * @param nazwa
   * @param kategoria
   * @param cena
   */
  public Potrawa(int id, String nazwa, String kategoria, double cena) {
    this.id = id;
    this.nazwa = nazwa;
    this.kategoria = kategoria;
    this.cena = cena;
  }

  /**
   * Metoda formatująca dane o potrawie.
   * @param numerPorzadkowy Numer na liście w swojej kategorii.
   * @return Sformatowane dane o potrawie.
   */
  public String toString(int numerPorzadkowy) {
    return numerPorzadkowy + ". " + nazwa + " - " + cena + "zł";
  }

  /**
   * Getter na id potrawy.
   * @return ID potrawy.
   */
  public int getId() {
    return id;
  }

  /**
   * Getter na nazwę potrawy.
   * @return Nazwa potrawy.
   */
  public String getNazwa() {
    return nazwa;
  }

  /**
   * Getter na kategorię potrawy.
   * @return Kategoria potrawy.
   */
  public String getKategoria() {
    return kategoria;
  }

  /**
   * Getter na cenę potrawy.
   * @return Cena potrawy.
   */
  public double getCena() {
    return cena;
  }
}
