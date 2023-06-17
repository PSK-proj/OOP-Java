package org.psk.client.util;

import org.junit.jupiter.api.Test;
import org.psk.shared.model.Potrawa;
import org.psk.shared.model.PotrawaWrapper;

import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuTest {

  @Test
  public void testGetSelectedPotrawy() {
    // Arrange
    MenuManager menuManager = new MenuManager();
    LinkedHashSet<PotrawaWrapper> selectedNapoje = new LinkedHashSet<>();
    LinkedHashSet<PotrawaWrapper> selectedDaniaGlowne = new LinkedHashSet<>();
    LinkedHashSet<PotrawaWrapper> selectedDesery = new LinkedHashSet<>();

    PotrawaWrapper napoj1 = new PotrawaWrapper(new Potrawa(1, "napoje", "napoje", 20), 1);
    PotrawaWrapper danie1 = new PotrawaWrapper(new Potrawa(2, "dania główne", "dania główne", 30), 1);
    PotrawaWrapper deser1 = new PotrawaWrapper(new Potrawa(3, "desery", "desery", 40), 1);

    selectedNapoje.add(napoj1);
    selectedDaniaGlowne.add(danie1);
    selectedDesery.add(deser1);

    menuManager.selectedNapoje.addAll(selectedNapoje);
    menuManager.selectedDaniaGlowne.addAll(selectedDaniaGlowne);
    menuManager.selectedDesery.addAll(selectedDesery);

    // Act
    LinkedHashSet<PotrawaWrapper> selectedPotrawy = menuManager.getSelectedPotrawy();

    // Assert
    assertEquals(3, selectedPotrawy.size());
    assertTrue(selectedPotrawy.contains(napoj1));
    assertTrue(selectedPotrawy.contains(danie1));
    assertTrue(selectedPotrawy.contains(deser1));
  }
}

