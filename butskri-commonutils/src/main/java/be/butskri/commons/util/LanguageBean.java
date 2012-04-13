package be.butskri.commons.util;

import java.io.Serializable;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

public class LanguageBean implements Serializable {

	private static final long serialVersionUID = -6752787432739489576L;

	// TODO remove all this code??
	private Locale locale;
	private LocaleResolver localeResolver;
	private SupportedLocales supportedLocales;

	public Locale getLocale() {
		// if (locale == null) {
		// initialiseer();
		// }
		// return locale;
		return LocaleContextHolder.getLocale();
	}

	public void setLocale(Locale locale) {
		// this.locale = supportedLocales.findSupportedLocale(locale);
		// localeResolver.setLocale(this.locale);
		LocaleContextHolder.setLocale(locale);
	}

	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}

	public void setSupportedLocales(SupportedLocales supportedLocales) {
		this.supportedLocales = supportedLocales;
	}

	private void initialiseer() {
		setLocale(localeResolver.getLocale());
	}
}
