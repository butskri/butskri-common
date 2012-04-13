package be.butskri.commons.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RandomizerBean {

	public static final String DEFAULT_ALGORITHM = "SHA1PRNG";
	public final static String ALPHANUMERICS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	private SecureRandom secureRandom;

	public RandomizerBean() {
		this(DEFAULT_ALGORITHM);
	}

	public RandomizerBean(String algorithm) {
		try {
			secureRandom = SecureRandom.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public double getRandomDouble() {
		return secureRandom.nextDouble();
	}

	public int getRandomInt() {
		return secureRandom.nextInt();
	}

	public long getRandomLong() {
		return secureRandom.nextLong();
	}

	public String getRandomString(int length, String charset) {
		StringBuilder sb = new StringBuilder();
		for (int loop = 0; loop < length; loop++) {
			int index = secureRandom.nextInt(charset.length());
			sb.append(charset.charAt(index));
		}
		return sb.toString();
	}
}
