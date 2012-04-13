package be.butskri.commons.util;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

public class SupportedLocales {

	private Locale defaultLocale;
	private List<Locale> locales;

	public Locale getDefaultLocale() {
		return defaultLocale;
	}

	public List<Locale> getLocales() {
		return locales;
	}

	public void setDefaultLocale(Locale defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public void setLocales(List<Locale> locales) {
		this.locales = locales;
	}

	public Locale findSupportedLocale(Locale locale) {
		Locale result = findLocale(locale);
		if (result == null) {
			result = defaultLocale;
		}
		return result;
	}

	private Locale findLocale(Locale locale) {
		Locale result = null;

		for (Locale aLocale : locales) {
			if (aLocale.equals(locale)) {
				result = locale;
				break;
			}
		}
		if (result == null) {
			if (!StringUtils.isEmpty(locale.getVariant())) {
				result = findLocale(new Locale(locale.getLanguage(), locale.getCountry()));
			} else if (!StringUtils.isEmpty(locale.getCountry())) {
				result = findLocale(new Locale(locale.getLanguage()));
			}
		}
		return result;
	}

}
