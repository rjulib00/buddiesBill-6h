package es.unileon.prg1.buddiesBill;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BuddiesTest {
	
	 @Rule
     public ExpectedException thrown= ExpectedException.none();
	

	@Test
	public void testBuddies() {
		Buddies grupito = new Buddies(4);
		assertNotNull(grupito);
	}


	@Test
	public void testGetCantidadmembers() {
		Buddies grupito = new Buddies(4);
		assertEquals(grupito.getNumberOfMembers(), 4);
	}

	@Test
	public void testAdd() throws BuddiesBillException {
		Buddies grupito = new Buddies(4);
		Buddy miembro1 = new Buddy("Juan", 16f);
		Buddy miembro2 = new Buddy("Manolo", 18.33f);
		Buddy miembro3 = new Buddy("Pepe", 9.23f);
		Buddy miembro4 = new Buddy("Felipe", 22.12f);
		assertTrue(grupito.add(miembro1));
		assertTrue(grupito.add(miembro2));
		assertTrue(grupito.add(miembro3));
		assertTrue(grupito.add(miembro4));
	}
	
	@Test
	public void testAddError() throws BuddiesBillException {
		Buddies grupito = new Buddies(4);
		Buddy miembro1 = new Buddy("Juan", 16f);
		Buddy miembro2 = new Buddy("Manolo", 18.33f);
		Buddy miembro3 = new Buddy("Pepe", 9.23f);
		Buddy miembro4 = new Buddy("Felipe", 22.12f);
		Buddy miembro5 = new Buddy("Manuel", 22.12f);
		assertTrue(grupito.add(miembro1));
		assertTrue(grupito.add(miembro2));
		assertTrue(grupito.add(miembro3));
		assertTrue(grupito.add(miembro4));
		thrown.expectMessage("Not allowed");
		assertFalse(grupito.add(miembro5));
	}

	@Test
	public void testSearchCase() throws BuddiesBillException {
		Buddies grupito = new Buddies(4);
		Buddy miembro1 = new Buddy("Juan", 16f);
		Buddy miembro2 = new Buddy("Manolo", 18.33f);
		Buddy miembro3 = new Buddy("Pepe", 9.23f);
		Buddy miembro4 = new Buddy("Felipe", 22.12f);
		grupito.add(miembro1);
		grupito.add(miembro2);
		grupito.add(miembro3);
		grupito.add(miembro4);
		assertEquals(grupito.search("Manolo"), miembro2);
	}
	
	@Test
	public void testSearchCaseFail() throws BuddiesBillException {
		Buddies grupito = new Buddies(4);
		Buddy miembro1 = new Buddy("Juan", 16f);
		Buddy miembro2 = new Buddy("Manolo", 18.33f);
		Buddy miembro3 = new Buddy("Pepe", 9.23f);
		Buddy miembro4 = new Buddy("Felipe", 22.12f);
		grupito.add(miembro1);
		grupito.add(miembro2);
		grupito.add(miembro3);
		grupito.add(miembro4);
		assertEquals(grupito.search("Juanjo"), null);
	}
	
	@Test
	public void testGetBalance() throws BuddiesBillException {
		Buddies grupito = new Buddies(4);
		Buddy miembro1 = new Buddy("Juan", 16f);
		Buddy miembro2 = new Buddy("Manolo", 18.33f);
		Buddy miembro3 = new Buddy("Pepe", 9.23f);
		Buddy miembro4 = new Buddy("Felipe", 22.12f);
		grupito.add(miembro1);
		grupito.add(miembro2);
		grupito.add(miembro3);
		grupito.add(miembro4);
		assertEquals(65.68f, grupito.getBalance(), 0.00f);
	}
	
	@Test
	public void testSearchIgnoreCase() throws BuddiesBillException {
		Buddies grupito = new Buddies(4);
		Buddy miembro1 = new Buddy("Juan", 16f);
		Buddy miembro2 = new Buddy("Manolo", 18.33f);
		Buddy miembro3 = new Buddy("Pepe", 9.23f);
		Buddy miembro4 = new Buddy("Felipe", 22.12f);
		grupito.add(miembro1);
		grupito.add(miembro2);
		grupito.add(miembro3);
		grupito.add(miembro4);
		assertEquals(grupito.search("manolo"), miembro2);
	}
	
	@Test
	public void testSearchIgnoreCaseFail() throws BuddiesBillException {
		Buddies grupito = new Buddies(4);
		Buddy miembro1 = new Buddy("Juan", 16f);
		Buddy miembro2 = new Buddy("Manolo", 18.33f);
		Buddy miembro3 = new Buddy("Pepe", 9.23f);
		Buddy miembro4 = new Buddy("Felipe", 22.12f);
		grupito.add(miembro1);
		grupito.add(miembro2);
		grupito.add(miembro3);
		grupito.add(miembro4);
		assertEquals(grupito.search("JuAnJo"), null);
	}

	@Test
	public void testRemove() throws BuddiesBillException {
		Buddies grupito = new Buddies(4);
		Buddy miembro1 = new Buddy("Juan", 16f);
		Buddy miembro2 = new Buddy("Manolo", 18.33f);
		Buddy miembro3 = new Buddy("Pepe", 9.23f);
		Buddy miembro4 = new Buddy("Felipe", 22.12f);
		grupito.add(miembro1);
		grupito.add(miembro2);
		grupito.add(miembro3);
		grupito.add(miembro4);
		assertTrue(grupito.remove(miembro3));
	}
	
	
	@Test (expected = Exception.class)
	public void testRemoveFailException() throws BuddiesBillException {
		Buddies grupito = new Buddies(4);
		Buddy miembro1 = new Buddy("Juan", 16f);
		Buddy miembro2 = new Buddy("Manolo", 18.33f);
		Buddy miembro3 = new Buddy("Juanjo", 18.33f);
		grupito.add(miembro1);
		grupito.add(miembro2);
		grupito.remove(miembro3);
		thrown.expectMessage("No buddy found");
		
	}


	@Test
	public void testCheckIfPresent() throws BuddiesBillException {
		Buddies grupito = new Buddies(4);
		Buddy miembro1 = new Buddy("Juan", 16f);
		Buddy miembro2 = new Buddy("Manolo", 18.33f);
		Buddy miembro3 = new Buddy("Pepe", 9.23f);
		Buddy miembro4 = new Buddy("Felipe", 22.12f);
		grupito.add(miembro1);
		grupito.add(miembro2);
		grupito.add(miembro3);
		grupito.add(miembro4);
		assertTrue(grupito.checkIfPresent(miembro3));
	}
	
	@Test
	public void testCheckIfPresentFail() throws BuddiesBillException {
		Buddies grupito = new Buddies(4);
		Buddy miembro1 = new Buddy("Juan", 16f);
		Buddy miembro2 = new Buddy("Manolo", 18.33f);
		Buddy miembro3 = new Buddy("Pepe", 9.23f);
		grupito.add(miembro1);
		grupito.add(miembro2);
		assertFalse(grupito.checkIfPresent(miembro3));
	}

	@Test
	public void testToString() throws BuddiesBillException {
		Buddies grupito = new Buddies(4);
		String aux = "Juan : " + 16f + "\nManolo : " + 18.33f + "\n";
		Buddy miembro1 = new Buddy("Juan", 16f);
		Buddy miembro2 = new Buddy("Manolo", 18.33f);
		grupito.add(miembro1);
		grupito.add(miembro2);
		assertEquals(grupito.toString(), aux);
	}
	
	@Test
	public void testGetList() throws BuddiesBillException{
		Buddies grupito = new Buddies(2);
		Buddy miembro1 = new Buddy("Juan", 16f);
		Buddy miembro2 = new Buddy("Manolo", 18.33f);
		grupito.add(miembro1);
		grupito.add(miembro2);
		Buddy matrix[] = {miembro1, miembro2};
		assertArrayEquals(grupito.getList(), matrix);
	}

}
