package es.unileon.prg1.buddiesBill;

import org.junit.*;
/**
 * 
* @author Rosales Robles David
*/
public class EventTest {

	
	String name;
	Buddies buddies;
	Movements movements;
	float balance;
	Event event;
	Payments payments;
	
	
	@Before
    public void setUp() throws BuddiesBillException {
		event = new Event("Cena", 8, 8);
		payments = new Payments ((1)*2);
        
       
    }
	
	@Test
	public void gettersTest() throws BuddiesBillException {
		Buddy buddy = new Buddy("David", 0f);
        Buddy buddy2 = new Buddy("Lucia", 0f);
        event.getBuddies().add(buddy);
        event.getBuddies().add(buddy2);
        Movement movement = new Movement ("drink", 40f, buddy);
        Movement movement2 = new Movement ("eat", 20f, buddy2);
        event.getMovements().add(movement);
        event.getMovements().add(movement2);
		event.settleUp();
		Assert.assertEquals("Cena", event.getName());
		Assert.assertEquals(60f, event.getBalance(),0);
		Assert.assertEquals(8, event.getBuddies().getNumberOfMembers());
		Assert.assertEquals("drink - 40.0 paid by David\neat - 20.0 paid by Lucia\n" , event.getMovements().toString());
		Assert.assertEquals("\nPayment: 10.0 from Lucia to David", event.getPayments().toString());
	}
	
	
	@Test
	public void settleUpTestIf() throws BuddiesBillException {
		Buddy buddy = new Buddy("David", 0f);
        Buddy buddy2 = new Buddy("Lucia", 0f);
        Buddy buddy3 = new Buddy("Paco", 0f);
        event.getBuddies().add(buddy);
        event.getBuddies().add(buddy2);
        event.getBuddies().add(buddy3);
        Movement movement = new Movement ("drink", 10f, buddy);
        Movement movement2 = new Movement ("eat", 41f, buddy3);
        event.getMovements().add(movement);
        event.getMovements().add(movement2);
		event.settleUp();
		Assert.assertEquals("\nPayment: 7.0 from David to Paco\nPayment: 17.0 from Lucia to Paco", event.getPayments().toString());
		
		
	}
	
	@Test
	public void settleUpTestElse() throws BuddiesBillException {
		Buddy buddy = new Buddy("David", 0f);
        Buddy buddy2 = new Buddy("Lucia", 0f);
        Buddy buddy3 = new Buddy("Paco", 0f);
        Buddy buddy4= new Buddy("Luis", 0f);
        event.getBuddies().add(buddy);
        event.getBuddies().add(buddy2);
        event.getBuddies().add(buddy4);
        Movement movement = new Movement ("drink", 3.45f, buddy);
        Movement movement2 = new Movement ("eat", 8f, buddy3);
        Movement movement3 = new Movement ("meal", 6f, buddy4);
        Movement movement4 = new Movement ("gas", 6f, buddy2);
        event.getMovements().add(movement);
        event.getMovements().add(movement2);
        event.getMovements().add(movement3);
        event.getMovements().add(movement4);
		event.settleUp();
		Assert.assertEquals("\nPayment: 0.8499999 from David to Lucia\nPayment: 0.8499999 from David to Luis", event.getPayments().toString());
		
		
	}
	
	

}
