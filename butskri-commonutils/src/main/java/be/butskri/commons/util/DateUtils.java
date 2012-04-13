package be.butskri.commons.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import be.butskri.commons.exception.ButskriRuntimeException;

public class DateUtils extends org.apache.commons.lang.time.DateUtils {

	private static final Pattern GELDIGE_DATUM_PATTERN = Pattern.compile("\\d{2}/?\\d{2}/?\\d{4}");
	private static final Pattern GELDIGE_TIJD_PATTERN = Pattern.compile("\\d{2}/?\\d{2}/?\\d{4} \\d{2}:?\\d{2}");
	private static final String SIMPLE_DATE_FORMAT = "dd/MM/yyyy";
	private static final String ALTERNATIVE_DATE_FORMAT = "ddMMyyyy";
	private static final String SIMPLE_TIME_FORMAT = "dd/MM/yyyy HH:mm";
	private static final String ALTERNATIVE_TIME_FORMAT1 = "ddMMyyyy HH:mm";
	private static final String ALTERNATIVE_TIME_FORMAT2 = "dd/MM/yyyy HHmm";
	private static final String ALTERNATIVE_TIME_FORMAT3 = "ddMMyyyy HHmm";
	private static DateFormat DATE_FORMAT = new SimpleDateFormat(SIMPLE_DATE_FORMAT);
	private static DateFormat TIME_FORMAT = new SimpleDateFormat(SIMPLE_TIME_FORMAT);
	private static Date currentTime;

	public static Date currentTime() {
		Date result = currentTime;
		if (result == null) {
			result = new Date();
		}
		return result;
	}

	public static Date clearTime(Date date) {
		return truncate(date, Calendar.DAY_OF_MONTH);
	}

	public static Date parseDate(String date) {
		try {
			if (!isGeldigeDatum(date)) {
				throw new ButskriRuntimeException("ongeldige_datum");
			}
			return parseDate(date, ArrayUtils.asArray(SIMPLE_DATE_FORMAT, ALTERNATIVE_DATE_FORMAT));
		} catch (ParseException e) {
			throw new ButskriRuntimeException("ongeldige_datum", e);
		}
	}

	public static Date parseTime(String time) {
		try {
			if (!isGeldigeTijd(time)) {
				throw new ButskriRuntimeException("ongeldige_tijd");
			}
			return parseDate(time, ArrayUtils.asArray(SIMPLE_TIME_FORMAT, ALTERNATIVE_TIME_FORMAT1,
					ALTERNATIVE_TIME_FORMAT2, ALTERNATIVE_TIME_FORMAT3));
		} catch (ParseException e) {
			throw new ButskriRuntimeException("ongeldige_tijd", e);
		}
	}

	public static String formatDate(Date date) {
		return DATE_FORMAT.format(date);
	}

	public static String formatTime(Date date) {
		return TIME_FORMAT.format(date);
	}

	static void setCurrentTime(Date date) {
		currentTime = date;
	}

	static void resetCurrentTime() {
		currentTime = null;
	}

	private static boolean isGeldigeDatum(String datum) {
		Matcher matcher = GELDIGE_DATUM_PATTERN.matcher(datum);
		return matcher.matches();
	}

	private static boolean isGeldigeTijd(String time) {
		Matcher matcher = GELDIGE_TIJD_PATTERN.matcher(time);

		return matcher.matches();
	}

}
