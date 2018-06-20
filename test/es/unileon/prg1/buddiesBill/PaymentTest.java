package es.unileon.prg1.buddiesBill;


import es.unileon.prg1.buddiesBill.Payment;
import es.unileon.prg1.buddiesBill.Buddy;
import org.junit.*;

/**
*
* @author Rosales Robles David
*/
public class PaymentTest {

	Buddy buddySender;
	Buddy buddyReceiver;
	Payment payment;
	
	@Before
    public void setUp() {
        buddySender = new Buddy("Lorena",20);
        buddyReceiver = new Buddy("David",10);
        payment = new Payment(buddySender, buddyReceiver, 5.0f);
    }
	
	@Test
	public void gettersTest() {
        Assert.assertEquals(buddySender, payment.getSender());
        Assert.assertEquals(buddyReceiver, payment.getReceiver());
        Assert.assertEquals(5.0f, payment.getMoney(), 0);
	}
	
	@Test
    public void toStringTest() {
        String paymentPrinted = "\nPayment: 5.0 from " + payment.getSender().getName() +" to " + payment.getReceiver().getName();
        Assert.assertEquals(paymentPrinted, payment.toString());
    }
	
}
