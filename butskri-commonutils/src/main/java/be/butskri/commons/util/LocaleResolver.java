package be.butskri.commons.util;

import java.util.Locale;

public interface LocaleResolver {

	void setLocale(Locale locale);

	Locale getLocale();
}
