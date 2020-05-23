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

}
