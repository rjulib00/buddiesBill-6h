/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.prg1.buddiesBill;

import es.unileon.prg1.buddiesBill.Movements;
import es.unileon.prg1.buddiesBill.BuddiesBillException;
import es.unileon.prg1.buddiesBill.Movement;
import es.unileon.prg1.buddiesBill.Buddy;
import org.junit.*;

/**
 *
 * @author Matias D. Jonatan
 */
public class MovementsTest {
    
    Movements movements;
    Movement dinner;
    Movement cinema;
    Buddy anne;
    Buddy frank;
    
    @Before
    public void setUp() throws BuddiesBillException {     
        movements = new Movements(2);
        anne = new Buddy("Anne", 12.12F);
        frank = new Buddy("Frank", 56.56F);
        dinner = new Movement("Dinner", 24.78F, anne);
        cinema = new Movement("Cinema", 20F, frank); 
    }
    
    @Test
    public void isAfterNextTest() throws BuddiesBillException {
        //null  null
        //next      
        //Cant go further, no more items
        Assert.assertEquals(false, movements.isAfterNext());        
        movements.add(dinner);
        movements.add(cinema);
        //dinner    null
        //          next
        movements._next = 0;
        //Can go further because there are 2 items and next points to the first
        Assert.assertEquals(true, movements.isAfterNext());   
        
    }
    
    @Test
    public void addMovementTest() throws BuddiesBillException {
        Assert.assertEquals(true, movements.add(dinner));        
        Assert.assertEquals(true, movements.add(cinema));
    }
    
    @Test(expected = BuddiesBillException.class)
    public void addMovementDupicatedTest() throws BuddiesBillException {
        Assert.assertEquals(true, movements.add(dinner));        
        Assert.assertEquals(false, movements.add(dinner));
    }    
    
    @Test(expected = BuddiesBillException.class)
    public void addMovementFullListTest() throws BuddiesBillException {
        Assert.assertEquals(true, movements.add(dinner));        
        Assert.assertEquals(true, movements.add(cinema));
        Movement gas = new Movement("Gas", 20F, frank);
        Assert.assertEquals(false, movements.add(gas));
    }
    
    @Test
    public void removeTest() throws BuddiesBillException {
        movements.add(dinner);
        movements.add(cinema);
        
        Assert.assertEquals(true, movements.remove(cinema));
        Assert.assertEquals(true, movements.remove(dinner));
    }
    
    @Test(expected = BuddiesBillException.class)
    public void removeNullTest() throws BuddiesBillException {
        movements.add(dinner);        
        Assert.assertEquals(true, movements.remove(cinema));
    }
    
    @Test
    public void restorePointerTest() throws BuddiesBillException {
        //movements empty
        movements.add(dinner);
        movements.add(cinema);
        //Added 2 movements, _next should be point to position 2
        Assert.assertEquals(2, movements._next);
        movements._next = 0;
        Assert.assertEquals(0, movements._next);
        //Retoring pointer position
        movements.restorePointer();
        //_next must be pointing to 2 after restore it
        Assert.assertEquals(2, movements._next);
    }
    
    @Test
    public void restorePointerNullTest() {
        //movements empty
        movements._next = 2;
        Assert.assertEquals(2, movements._next);
        //Retoring pointer position
        movements.restorePointer();
        Assert.assertEquals(0, movements._next);
    }
    
    @Test
    public void indexOfTest() throws BuddiesBillException {
        movements.add(cinema);
        movements.add(dinner);
        
        Assert.assertEquals(0, movements.indexOf(cinema));
        Assert.assertEquals(1, movements.indexOf(dinner));
    }
    
    @Test
    public void collapseTest() throws BuddiesBillException {
        //null  null
        movements.add(cinema);
        //cinema    null
        movements.add(dinner);
        //cinema    dinner
        movements.remove(cinema);
        //diner null
        Assert.assertEquals(null, movements.list[1]);
    }
    
    @Test
    public void toStringTest() throws BuddiesBillException {
        movements.add(cinema);
        movements.add(dinner);
        String result = "Cinema - 20.0 paid by Frank" + '\n' + "Dinner - 24.78 paid by Anne" + '\n';
        Assert.assertEquals(result, movements.toString());
    }
    
}
