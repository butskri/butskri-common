package be.butskri.commons.types;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import be.butskri.commons.exception.ButskriRuntimeException;

public class Rekeningnummer {

	private static final Pattern PATTERN = Pattern.compile("\\d{12}");

	private String nummer;

	public Rekeningnummer(String nummer) {
		this.nummer = nummer.replaceAll("-", "");

		if (!voldoetAanPattern() || !isModulo97Compatibel()) {
			throw new ButskriRuntimeException("ongeldig_rekeningnummer");
		}
	}

	private boolean isModulo97Compatibel() {
		Long prefix = Long.valueOf(nummer.substring(0, nummer.length() - 2));
		Long restBijDelingDoor97 = prefix % 97;
		Long suffix = Long.valueOf(nummer.substring(nummer.length() - 2));
		return restBijDelingDoor97.equals(suffix);
	}

	private boolean voldoetAanPattern() {
		Matcher matcher = PATTERN.matcher(nummer);
		return matcher.matches();
	}

	public String format() {
		StringBuffer result = new StringBuffer();
		result.append(nummer.substring(0, 3));
		result.append("-");
		result.append(nummer.substring(3, 10));
		result.append("-");
		result.append(nummer.substring(10));
		return result.toString();
	}

	@Override
	public int hashCode() {
		return nummer.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj != null && obj instanceof Rekeningnummer) {
			Rekeningnummer anderRekeningnummer = (Rekeningnummer) obj;
			result = this.nummer.equals(anderRekeningnummer.nummer);
		}
		return result;
	}

	@Override
	public String toString() {
		return format();
	}
}
