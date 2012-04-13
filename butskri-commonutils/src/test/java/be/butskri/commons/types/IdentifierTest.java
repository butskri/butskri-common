package be.butskri.commons.types;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import be.butskri.commons.test.JUnit4ButskriClassRunner;

@RunWith(JUnit4ButskriClassRunner.class)
public class IdentifierTest {

	private static final Long ID = 15l;

	@Test
	public void getIdGeeftIdTerug() {
		Identifier identifier = new Identifier(ID);

		assertEquals(ID, identifier.getId());
	}

	@Test
	public void equalsGeeftTrueTerugVoorIdentifiersMetGelijkeId() {
		Identifier identifier1 = new Identifier(15l);
		Identifier identifier2 = new Identifier(15l);

		assertTrue(identifier1.equals(identifier2));
		assertTrue(identifier2.equals(identifier1));
	}

	@Test
	public void equalsGeeftFalseTerugVoorIdentifiersMetVerschillendId() {
		Identifier identifier1 = new Identifier(15l);
		Identifier identifier2 = new Identifier(16l);

		assertFalse(identifier1.equals(identifier2));
		assertFalse(identifier2.equals(identifier1));
	}

	@Test
	public void hashCodeIsGelijkVoorIdentifiersMetGelijkeId() {
		Identifier identifier1 = new Identifier(15l);
		Identifier identifier2 = new Identifier(15l);

		assertEquals(identifier1.hashCode(), identifier2.hashCode());
	}

	@Test
	public void toStringGeeftStringWaardeVanIdTerug() {
		Identifier identifier = new Identifier(1234l);

		assertEquals("1234", identifier.toString());
	}

}
