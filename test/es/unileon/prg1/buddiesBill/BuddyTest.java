package es.unileon.prg1.buddiesBill;

import static org.junit.Assert.*;

import org.junit.Test;


public class BuddyTest {

	@Test
	public void testBuddy() {
		Buddy bud = new Buddy("Pepe", 15.03f);
		assertNotNull(bud);
	}

	@Test
	public void testSetName() {
		Buddy bud = new Buddy("Pepe", 15.03f);
		bud.setName("Juan");
		assertEquals(bud.getName(), "Juan");
	}

	@Test
	public void testSetMoney() {
		Buddy bud = new Buddy("Pepe", 15.03f);
		bud.setMoney(17f);
		assertEquals(17f, bud.getMoney(), 0.00f);
	}
	
	@Test
	public void testSetMoneyNotFloat() {
		Buddy bud = new Buddy("Pepe", 15.03f);
		bud.setMoney(17);
		assertEquals(17f, bud.getMoney(), 0.00f);
	}

	@Test
	public void testGetName() {
		Buddy bud = new Buddy("Pepe", 15.03f);
		assertEquals(bud.getName(), "Pepe");
	}

	@Test
	public void testGetMoney() {
		Buddy bud = new Buddy("Pepe", 15.03f);
		assertEquals(15.03f, bud.getMoney(), 0.00f);
	}

	@Test
	public void testToString() {
		Buddy bud = new Buddy("Pepe", 15.03f);
		assertEquals(bud.toString(), "Pepe : 15.03");
	}

}
