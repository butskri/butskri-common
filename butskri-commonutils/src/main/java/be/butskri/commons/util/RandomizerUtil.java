package be.butskri.commons.util;

import java.math.BigDecimal;

import be.butskri.commons.types.Bedrag;

public class RandomizerUtil {

	public static final int SHORT = 10;
	public static final int LONG = 150;
	private static RandomizerBean randomizer = new RandomizerBean();

	public static String randomShortAlphanumeric() {
		return randomizer.getRandomString(SHORT, RandomizerBean.ALPHANUMERICS);
	}

	public static String randomLongAlphanumeric() {
		return randomizer.getRandomString(LONG, RandomizerBean.ALPHANUMERICS);
	}

	public static Bedrag randomBedrag() {
		return new Bedrag(new BigDecimal(randomizer.getRandomDouble()));
	}

	public static Long randomLong() {
		return randomizer.getRandomLong();
	}
}
