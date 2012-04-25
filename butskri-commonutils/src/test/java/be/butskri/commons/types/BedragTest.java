package be.butskri.commons.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.ExpectedExceptionRule;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import be.butskri.commons.exception.ButskriRuntimeException;
import be.butskri.commons.test.junit.ExpectedThrowable;

@RunWith(BlockJUnit4ClassRunner.class)
public class BedragTest {
	
	@Rule
	public ExpectedExceptionRule expectedExceptionRule = new ExpectedExceptionRule();

	@Test
	public void constructorRondWaardeAfTotOpTweeCijfersNaDeKomma() {
		Bedrag bedrag = new Bedrag(BigDecimal.valueOf(15.362));

		assertEquals(BigDecimal.valueOf(15.36), bedrag.getValue());
	}

	@Test
	public void constructorMetStringAanvaardWaardeMetPunt() {
		Bedrag bedrag = new Bedrag("15.36");

		assertEquals(BigDecimal.valueOf(15.36), bedrag.getValue());
	}

	@Test
	public void constructorMetStringAanvaardWaardeMetKomma() {
		Bedrag bedrag = new Bedrag("15,36");

		assertEquals(BigDecimal.valueOf(15.36), bedrag.getValue());
	}

	@Test
	public void constructorMetStringAanvaardNegatieveWaarde() {
		Bedrag bedrag = new Bedrag("-15,36");

		assertEquals(BigDecimal.valueOf(-15.36), bedrag.getValue());
	}

	@Test
	@ExpectedThrowable(value = "ongeldig_bedrag", type = ButskriRuntimeException.class)
	public void constructorMetOngeldigeStringWaardeGooitExceptionIndienOngeldigeWaarde() {
		new Bedrag("ongeldig");
	}

	@Test
	@ExpectedThrowable(value = "ongeldig_bedrag", type = ButskriRuntimeException.class)
	public void constructorMetStringBeginnendMetLetterGooitException() {
		new Bedrag("h15,36");
	}

	@Test
	@ExpectedThrowable(value = "ongeldig_bedrag", type = ButskriRuntimeException.class)
	public void constructorMetStringEindigendMetLetterGooitException() {
		new Bedrag("15,36h");
	}

	@Test
	@ExpectedThrowable(value = "ongeldig_bedrag", type = ButskriRuntimeException.class)
	public void constructorMetStringMetLetterInHetMiddenGooitException() {
		new Bedrag("1h5,36");
	}

	@Test
	@ExpectedThrowable(value = "ongeldig_bedrag", type = ButskriRuntimeException.class)
	public void constructorMetStringWaardeGooitExceptionIndienMeerDanTweeCijfersNaDeKomma() {
		new Bedrag("15.362");
	}

	@Test
	public void equalsGeeftTrueTerugIndienWaardenGelijkZijn() {
		Bedrag bedrag1 = new Bedrag(BigDecimal.valueOf(15.36));
		Bedrag bedrag2 = new Bedrag(BigDecimal.valueOf(15.36));

		assertTrue(bedrag1.equals(bedrag2));
		assertTrue(bedrag2.equals(bedrag1));
	}

	@Test
	public void equalsGeeftFalseTerugVoorNullWaarde() {
		Bedrag bedrag = new Bedrag(BigDecimal.valueOf(15.36));

		assertFalse(bedrag.equals(null));
	}

	@Test
	public void equalsGeeftFalseTerugIndienWaardenNietGelijkZijn() {
		Bedrag bedrag1 = new Bedrag(BigDecimal.valueOf(15.36));
		Bedrag bedrag2 = new Bedrag(BigDecimal.valueOf(15.37));

		assertFalse(bedrag1.equals(bedrag2));
		assertFalse(bedrag2.equals(bedrag1));
	}

	@Test
	public void equalsGeeftFalseTerugVoorObjectVanEenAnderType() {
		Bedrag bedrag = new Bedrag(BigDecimal.valueOf(15.36));
		Object anderObject = new Object();

		assertFalse(bedrag.equals(anderObject));
	}

	@Test
	public void gelijkeBedragenHebbenGelijkeHashcodes() {
		Bedrag bedrag1 = new Bedrag(BigDecimal.valueOf(15.36));
		Bedrag bedrag2 = new Bedrag(BigDecimal.valueOf(15.36));

		assertEquals(bedrag1.hashCode(), bedrag2.hashCode());
	}

	@Test
	public void getBedragMaaktBedragMetOpgegevenWaarde() {
		BigDecimal waarde = BigDecimal.valueOf(15.36);
		Bedrag bedrag = new Bedrag(waarde);

		assertEquals(bedrag, Bedrag.getBedrag(waarde));
	}

	@Test
	public void getBedragGeeftNullTerugVoorWaardeNull() {
		assertNull(Bedrag.getBedrag(null));
	}

	@Test
	public void getValueGeeftBigDecimalWaardeTerugVanOpgegevenBedrag() {
		BigDecimal waarde = BigDecimal.valueOf(15.36);
		Bedrag bedrag = new Bedrag(waarde);

		assertEquals(waarde, Bedrag.getValue(bedrag));
	}

	@Test
	public void getValueGeeftNullTerugIndienOpgegevenBedragNullIs() {
		assertNull(Bedrag.getValue(null));
	}

	@Test
	public void formatGeeftBedragGeformatteerdMetEenKommaTerug() {
		Bedrag bedrag = new Bedrag("17.23");

		assertEquals("17,23", bedrag.format());
	}

}
