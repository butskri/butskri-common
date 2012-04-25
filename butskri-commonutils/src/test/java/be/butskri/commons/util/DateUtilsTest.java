package be.butskri.commons.util;

import static be.butskri.commons.test.DateAssert.assertDatum;
import static be.butskri.commons.test.DateAssert.assertGeenMilliseconden;
import static be.butskri.commons.test.DateAssert.assertMiddernacht;
import static be.butskri.commons.test.DateAssert.assertTijd;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.ExpectedExceptionRule;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import be.butskri.commons.exception.ButskriRuntimeException;
import be.butskri.commons.test.junit.ExpectedThrowable;

@RunWith(BlockJUnit4ClassRunner.class)
public class DateUtilsTest {

	@Rule
	public ExpectedExceptionRule expectedExceptionRule = new ExpectedExceptionRule();

	@Test
	public void parseDateZetDatumMetSlashesOmNaarDateObject() {
		Date datum = DateUtils.parseDate("17/09/2003");

		assertDatum(2003, 9, 17, datum);
		assertMiddernacht(datum);
	}

	@Test
	public void parseDateZetDatumZonderSlashesOmNaarDateObject() {
		Date datum = DateUtils.parseDate("17092003");

		assertDatum(2003, 9, 17, datum);
		assertMiddernacht(datum);
	}

	@Test
	@ExpectedThrowable(value = "ongeldige_datum", type = ButskriRuntimeException.class)
	public void parseDateKanGeenDatumOmzettenMetJaarBestaandeUitTweeCijfers() {
		DateUtils.parseDate("170903");
	}

	@Test
	@ExpectedThrowable(value = "ongeldige_datum", type = ButskriRuntimeException.class)
	public void parseDateKanGeenOngeldigeDatumParsen() {
		DateUtils.parseDate("ongeldig");
	}

	@Test
	public void parseTimeZetStringOmNaarDateObject() {
		Date datum = DateUtils.parseTime("17/09/2003 15:47");

		assertDatum(2003, 9, 17, datum);
		assertTijd(15, 47, 0, datum);
		assertGeenMilliseconden(datum);
	}

	@Test
	public void parseTimeZetStringZonderSlashesOmNaarDateObject() {
		Date datum = DateUtils.parseTime("17092003 15:47");

		assertDatum(2003, 9, 17, datum);
		assertTijd(15, 47, 0, datum);
		assertGeenMilliseconden(datum);
	}

	@Test
	public void parseTimeZetStringZonderDubbelPuntOmNaarDateObject() {
		Date datum = DateUtils.parseTime("17/09/2003 1547");

		assertDatum(2003, 9, 17, datum);
		assertTijd(15, 47, 0, datum);
		assertGeenMilliseconden(datum);
	}

	@Test
	public void parseTimeZetStringZonderSlashesEnDubbelPuntOmNaarDateObject() {
		Date datum = DateUtils.parseTime("17092003 1547");

		assertDatum(2003, 9, 17, datum);
		assertTijd(15, 47, 0, datum);
		assertGeenMilliseconden(datum);
	}

	@Test
	@ExpectedThrowable(value = "ongeldige_tijd", type = ButskriRuntimeException.class)
	public void parseTimeKanGeenStringOmzettenMetJaarBestaandeUitTweeCijfers() {
		DateUtils.parseTime("170903 1547");
	}

	@Test
	@ExpectedThrowable(value = "ongeldige_tijd", type = ButskriRuntimeException.class)
	public void parseTimeKanGeenStringOmzettenZonderTijdsaanduiding() {
		DateUtils.parseTime("17/09/2003");
	}

	@Test
	@ExpectedThrowable(value = "ongeldige_tijd", type = ButskriRuntimeException.class)
	public void parseTimeKanGeenStringOmzettenZonderDatumaanduiding() {
		DateUtils.parseTime("15:47");
	}

	@Test
	@ExpectedThrowable(value = "ongeldige_tijd", type = ButskriRuntimeException.class)
	public void parseTimeKanGeenOngeldigeTijdParsen() {
		DateUtils.parseTime("ongeldig");
	}

	@Test
	public void formatDateFormatteertDatum() {
		Date datum = DateUtils.parseDate("02/07/2004");

		assertEquals("02/07/2004", DateUtils.formatDate(datum));
	}

	@Test
	public void formatTimeFormatteertDatumEnTijd() {
		Date datum = DateUtils.parseTime("02/07/2004 17:14");

		assertEquals("02/07/2004 17:14", DateUtils.formatTime(datum));
	}

	@Test
	public void clearTimeZetTijdOpMiddernacht() {
		Date datum = DateUtils.clearTime(new Date());

		assertMiddernacht(datum);
	}
}
