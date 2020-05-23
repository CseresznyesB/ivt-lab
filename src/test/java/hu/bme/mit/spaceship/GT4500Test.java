package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;

  private TorpedoStore mockpts;
  private TorpedoStore mocksts;

  @BeforeEach
  public void init(){
    //this.ship = new GT4500();

    mockpts=mock(TorpedoStore.class);
    mocksts=mock(TorpedoStore.class);

    this.ship=new GT4500(mockpts, mocksts);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockpts.fire(1)).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    verify(mockpts, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockpts.fire(1)).thenReturn(true);
    when(mocksts.fire(1)).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.ALL);
    // Assert
    verify(mockpts, times(1)).fire(1);
    verify(mocksts, times(1)).fire(1);
  }

  @Test
  public void fireSecondaryFirst(){
    // Arrange
    when(mocksts.fire(1)).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    assertEquals(true, result);
    verify(mocksts, times(1)).fire(1);
  }

  @Test
  public void secondaryEmpty(){
    // Arrange
    when(mocksts.isEmpty()).thenReturn(true);
    when(mockpts.fire(1)).thenReturn(true);
    when(mockpts.isEmpty()).thenReturn(false);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    assertEquals(true, result);
    verify(mockpts, times(2)).fire(1);
  }

  @Test
  public void bothEmpty(){
    // Arrange
    when(mocksts.isEmpty()).thenReturn(true);
    when(mockpts.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    assertEquals(false, result);
    verify(mockpts, times(0)).fire(1);
    verify(mocksts, times(0)).fire(1);
  }

  @Test
  public void primaryEmpty(){
    // Arrange
    when(mockpts.isEmpty()).thenReturn(true);
    when(mocksts.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    assertEquals(true, result);
    verify(mocksts, times(1)).fire(1);
  }

  @Test
  public void fireBoth(){
    // Arrange
    when(mockpts.fire(1)).thenReturn(true);
    when(mocksts.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    // Assert
    assertEquals(true, result);
    verify(mocksts, times(1)).fire(1);
    verify(mockpts, times(1)).fire(1);
  }

  // Based on code only: firingMode:SINGLE, wasPrimaryFiredLast:false,
  // primaryTorpedoStore.isEmpty():false

  @Test
  public void codeBaseTest(){
     // Arrange
     when(mockpts.fire(1)).thenReturn(true);
     when(mockpts.isEmpty()).thenReturn(false);
     // Act
     boolean result = ship.fireTorpedo(FiringMode.SINGLE);
     // Assert
     assertEquals(true, result);
     verify(mockpts, times(1)).fire(1);
  }

  /*@Test
  public void fullCoverageTest(){
    // Arrange
    when(mocksts.isEmpty()).thenReturn(true);
    when(mockpts.fire(1)).thenReturn(true);
    when(mockpts.isEmpty()).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    assertEquals(false, result);
  }*/

  @Test
  public void fullCoverageTest1(){
    // Arrange
    when(mocksts.isEmpty()).thenReturn(true);
    when(mockpts.fire(1)).thenReturn(true);
    when(mockpts.isEmpty()).thenReturn(false);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    when(mocksts.isEmpty()).thenReturn(true);
    when(mockpts.fire(1)).thenReturn(true);
    when(mockpts.isEmpty()).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    assertEquals(false, result);
    //verify(mockpts, times(1)).fire(1);
  }

  @Test
  public void fullCoverageTest2(){
    // Arrange
    when(mocksts.isEmpty()).thenReturn(true);
    when(mockpts.isEmpty()).thenReturn(false);
    when(mockpts.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    // Assert
    assertEquals(false, result);
    verify(mockpts, times(0)).fire(1);
    verify(mocksts, times(0)).fire(1);
  }

  @Test
  public void fullCoverageTest3(){
    // Arrange
    when(mocksts.isEmpty()).thenReturn(false);
    when(mockpts.isEmpty()).thenReturn(true);
    when(mocksts.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    // Assert
    assertEquals(false, result);
    verify(mockpts, times(0)).fire(1);
    verify(mocksts, times(0)).fire(1);
  }

  @Test
  public void fullCoverageTest4(){
    // Arrange
    when(mockpts.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    // Assert
    assertEquals(false, result);
    verify(mockpts, times(1)).fire(1);
    verify(mocksts, times(1)).fire(1);
  }

  @Test
  public void fullCoverageTest5(){
    // Arrange
    when(mocksts.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    // Assert
    assertEquals(false, result);
    verify(mockpts, times(1)).fire(1);
    verify(mocksts, times(0)).fire(1);
  }
}
