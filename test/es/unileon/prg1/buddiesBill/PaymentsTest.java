package es.unileon.prg1.buddiesBill;

import es.unileon.prg1.buddiesBill.Payment;
import es.unileon.prg1.buddiesBill.Payments;
import es.unileon.prg1.buddiesBill.Buddy;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PaymentsTest {

	Payments payments;
    Payment	payment1;
    Payment payment2;
    
    @Before
    public void setUp() {     
        payments = new Payments(2);
        Buddy Lorena = new Buddy("Lorena",0);
        Buddy David = new Buddy("David",0);
        Buddy Geralt = new Buddy("Geralt",20);
        payment1 = new Payment(Lorena, Geralt, 10f);
        payment2 = new Payment(David, Geralt, 10f); 
    }
	
	
    @Test
    public void addPaymentTest() {
        Assert.assertEquals(true, payments.add(payment1));        
    }
    
    @Test
    public void checkIfPresentTest() {
    	payments.add(payment1);
        Assert.assertEquals(true, payments.checkIfPresent(payment1));
        payments.add(payment2);
        Assert.assertEquals(false, payments.checkIfPresent(payment1));
        
    }
    
    @Test
    public void toStringTest() {
    	payments.add(payment1);
    	payments.add(payment2);
        String paymentPrinted = "\nPayment: 10.0 from Lorena to Geralt\nPayment: 10.0 from David to Geralt";
        Assert.assertEquals(paymentPrinted, payments.toString());
    }
	
}


