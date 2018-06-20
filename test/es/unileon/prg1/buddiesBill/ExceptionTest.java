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
public class ExceptionTest {
    
    @Test(expected = BuddiesBillException.class)
    public void testMessage() throws BuddiesBillException {
        Movements movements = new Movements(8);
        Buddy buddy = new Buddy("Jonatan", 21.21F);
        Movement dinner = new Movement("Dinner", 12.43F, buddy);
        movements.remove(dinner);
    }
}
