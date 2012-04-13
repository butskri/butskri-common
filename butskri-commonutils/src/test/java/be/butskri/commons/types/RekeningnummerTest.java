package be.butskri.commons.types;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import be.butskri.commons.exception.ButskriRuntimeException;
import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.commons.test.junit.ExpectedThrowable;

@RunWith(JUnit4ButskriClassRunner.class)
public class RekeningnummerTest {

	@Test
	@ExpectedThrowable(value = "ongeldig_rekeningnummer", type = ButskriRuntimeException.class)
	public void ongeldigRekeningnummerAanmakenGooitException() {
		new Rekeningnummer("ongeldigRekeningnummer");
	}

	@Test
	@ExpectedThrowable(value = "ongeldig_rekeningnummer", type = ButskriRuntimeException.class)
	public void rekeningnummerAanmakenMetTeVeelGetallenGooitException() {
		new Rekeningnummer("0416109001116");
	}

	@Test
	@ExpectedThrowable(value = "ongeldig_rekeningnummer", type = ButskriRuntimeException.class)
	public void rekeningnummerMetFouteControleCijfersGooitException() {
		new Rekeningnummer("416109001117");
	}

	@Test
	public void geldigRekeningnummerKanAangemaaktWorden() {
		new Rekeningnummer("416109001116");
		new Rekeningnummer("416-1090011-16");
	}

	@Test
	public void formatGeeftGeformatteerdRekeningnummerTerug() {
		assertEquals("416-1090011-16", new Rekeningnummer("416109001116").format());
	}

	@Test
	public void toStringGeeftGeformatteerdRekeningnummerTerug() {
		assertEquals("416-1090011-16", new Rekeningnummer("416109001116").toString());
	}

	@Test
	public void equalsGeeftTrueTerugIndienTweeRekeningnummersHetzelfdeZijn() {
		assertTrue(new Rekeningnummer("416-1090011-16").equals(new Rekeningnummer("416109001116")));
	}

	@Test
	public void equalsGeeftFalseTerugIndienTweeRekeningnummersNietHetzelfdeZijn() {
		assertFalse(new Rekeningnummer("416-1090011-16").equals(new Rekeningnummer("416109001217")));
	}

	@Test
	public void hashCodeGeeftZelfdeWaardeTerugIndienTweeRekeningnummersHetzelfdeZijn() {
		assertEquals(new Rekeningnummer("416-1090011-16").hashCode(), new Rekeningnummer("416109001116").hashCode());
	}

}
