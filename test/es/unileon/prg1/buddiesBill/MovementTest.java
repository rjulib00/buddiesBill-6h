/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.prg1.buddiesBill;

import es.unileon.prg1.buddiesBill.BuddiesBillException;
import es.unileon.prg1.buddiesBill.Movement;
import es.unileon.prg1.buddiesBill.Buddy;
import org.junit.*;

/**
 *
 * @author Matias D. Jonatan
 */
public class MovementTest {
   
    Movement movement;
    Buddy buddy;
    
    @Before
    public void setUp() throws BuddiesBillException {
        buddy = new Buddy("Anna", 21.21F);
        movement = new Movement("Dinner", 24.78F, buddy);
    }

    @Test
    public void gettersTest() {
        Assert.assertEquals("Dinner", movement.getName());
        Assert.assertEquals(24.78F, movement.getMoney(), 0.001);
        Assert.assertEquals(buddy, movement.getBuddy());
    }
    
    @Test
    public void equalsTest() throws BuddiesBillException {
        //Test if equal
        Movement movement2 = new Movement("Dinner", 24.78F, buddy);
        Assert.assertEquals(true, movement.equals(movement2));
        
        //Test if name not equal
        movement2 = new Movement("Cinema", 24.78F, buddy);
        Assert.assertEquals(false, movement.equals(movement2));
        
        //Test if money not equal
        movement2 = new Movement("Dinner", 12.12F, buddy);
        Assert.assertEquals(true, movement.equals(movement2));
        
        //Test if buddy not equal
        Buddy buddy2 = new Buddy("Susanne", 33.33F);
        movement2 = new Movement("Dinner", 24.78F, buddy2);
        Assert.assertEquals(true, movement.equals(movement2));
    }
    
    @Test
    public void toStringTest() {
        String movementPrinted = "Dinner - 24.78 paid by Anna";
        Assert.assertEquals(movementPrinted, movement.toString());
    }

}
