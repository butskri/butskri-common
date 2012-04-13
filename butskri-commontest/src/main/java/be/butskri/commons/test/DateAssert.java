package be.butskri.commons.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

// TODO testen schrijven voor deze klasse
public class DateAssert {

	public static void assertDatum(int jaar, int maand, int dag, Date datum) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datum);

		assertEquals(jaar, calendar.get(Calendar.YEAR));
		assertEquals(maand, calendar.get(Calendar.MONTH) + 1);
		assertEquals(dag, calendar.get(Calendar.DAY_OF_MONTH));
	}

	public static void assertMiddernacht(Date datum) {
		assertTijd(0, 0, 0, datum);
		assertGeenMilliseconden(datum);
	}

	public static void assertTijd(int uren, int minuten, int seconden, Date datum) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datum);

		assertEquals(uren, calendar.get(Calendar.HOUR_OF_DAY));
		assertEquals(minuten, calendar.get(Calendar.MINUTE));
		assertEquals(seconden, calendar.get(Calendar.SECOND));
	}

	public static void assertGeenMilliseconden(Date datum) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datum);

		assertEquals(0, calendar.get(Calendar.MILLISECOND));
	}
}
