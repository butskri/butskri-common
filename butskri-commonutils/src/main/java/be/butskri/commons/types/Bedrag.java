package be.butskri.commons.types;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParsePosition;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import be.butskri.commons.exception.ButskriRuntimeException;
import be.butskri.commons.util.Locales;

public class Bedrag implements Serializable {

	private static final long serialVersionUID = 8640355285816745459L;

	private static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00",
			DecimalFormatSymbols.getInstance(Locales.NL));
	private static final Pattern PATTERN = Pattern
			.compile("[\\+-]?\\d+[\\.\\,]?\\d{0,2}");
	private static int SCALE = 2;

	static {
		DECIMAL_FORMAT.setParseBigDecimal(true);
	}

	public static Bedrag getBedrag(BigDecimal value) {
		Bedrag result = null;
		if (value != null) {
			result = new Bedrag(value);
		}
		return result;
	}

	public static BigDecimal getValue(Bedrag bedrag) {
		BigDecimal result = null;
		if (bedrag != null) {
			result = bedrag.getValue();
		}
		return result;
	}

	private static BigDecimal parseBigDecimal(String stringValue) {
		stringValue = stringValue.replaceAll("\\.", ",");

		ParsePosition parsePosition = new ParsePosition(0);
		BigDecimal result = (BigDecimal) DECIMAL_FORMAT.parse(stringValue,
				parsePosition);
		if (parsePosition.getIndex() != stringValue.length()
				|| result.scale() > 2) {
			throw new IllegalArgumentException("ongeldig_bedrag");
		}

		return result;
	}

	private static boolean voldoetAanPattern(String value) {
		Matcher matcher = PATTERN.matcher(value);
		return matcher.matches();
	}

	private BigDecimal value;

	public Bedrag(BigDecimal value) {
		this.value = value.setScale(SCALE, BigDecimal.ROUND_HALF_UP);
	}

	public Bedrag(String value) {
		if (!voldoetAanPattern(value)) {
			throw new ButskriRuntimeException("ongeldig_bedrag");
		}
		this.value = parseBigDecimal(value);

	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (obj != null && obj instanceof Bedrag) {
			Bedrag otherAmount = (Bedrag) obj;
			result = this.value.equals(otherAmount.value);
		}
		return result;
	}

	public String format() {
		return DECIMAL_FORMAT.format(value);
	}

	public BigDecimal getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

}
