package org.psk.shared.model;

import java.util.Objects;

public class PotrawaWrapper {
  private Potrawa potrawa;
  private String displayString;

  public PotrawaWrapper(Potrawa potrawa, int numerPorzadkowy) {
    this.potrawa = potrawa;
    this.displayString = numerPorzadkowy + ". " + potrawa.getNazwa() + " - " + potrawa.getCena() + "zł";
  }

  public Potrawa getPotrawa() {
    return potrawa;
  }

  public String getDisplayString() {
    return displayString;
  }

  @Override
  public String toString() {
    return displayString;
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PotrawaWrapper that = (PotrawaWrapper) o;
    return potrawa.getId() == that.potrawa.getId();
  }

  @Override
  public int hashCode() {
    return Objects.hash(potrawa.getId());
  }
}

