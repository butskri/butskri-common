package be.butskri.commons.util;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Locale;

import org.apache.commons.lang.LocaleUtils;
import org.junit.Before;
import org.junit.Test;

public class SupportedLocalesTest {

	private static final Locale DEFAULT_LOCALE = Locale.CANADA;
	private static final Locale UNSUPPORTED_LOCALE = Locale.CHINESE;
	private static final Locale NL_BE_VL1 = LocaleUtils.toLocale("nl_BE_VL1");
	private static final Locale NL_BE_VL2 = LocaleUtils.toLocale("nl_BE_VL2");
	private static final Locale NL_BE = LocaleUtils.toLocale("nl_BE");
	private static final Locale NL_NL = LocaleUtils.toLocale("nl_NL");
	private static final Locale NL = LocaleUtils.toLocale("nl");
	private static final Locale FR_BE = LocaleUtils.toLocale("fr_BE");
	private static final Locale FR_FR = LocaleUtils.toLocale("fr_FR");
	private static final Locale FR = LocaleUtils.toLocale("fr");

	private SupportedLocales supportedLocales;

	@Before
	public void setUp() {
		supportedLocales = new SupportedLocales();
		supportedLocales.setDefaultLocale(DEFAULT_LOCALE);

	}

	@Test
	public void findSupportedLocaleGeeftLocaleTerugIndienDezeVoorkomtInLijstVanLocales() {
		supportedLocales.setLocales(Arrays.asList(NL, NL_BE, NL_BE_VL1, FR_FR, FR_BE, FR));

		assertEquals(NL_BE_VL1, supportedLocales.findSupportedLocale(NL_BE_VL1));
		assertEquals(NL_BE, supportedLocales.findSupportedLocale(NL_BE));
		assertEquals(NL, supportedLocales.findSupportedLocale(NL));
	}

	@Test
	public void findSupportedLocaleGeeftDefaultTerugVoorEenNietOndersteundeLocale() {
		supportedLocales.setLocales(Arrays.asList(NL_BE, NL_BE_VL1, FR_FR, FR_BE, FR));

		assertEquals(DEFAULT_LOCALE, supportedLocales.findSupportedLocale(NL));
		assertEquals(DEFAULT_LOCALE, supportedLocales.findSupportedLocale(UNSUPPORTED_LOCALE));
	}

	@Test
	public void findSupportedLocaleGeeftLocaleTerugMetZelfdeLanguageEnCountryAlsVariantVerschillendIs() {
		supportedLocales.setLocales(Arrays.asList(NL, NL_BE, NL_BE_VL1, FR_FR, FR_BE, FR));

		assertEquals(NL_BE, supportedLocales.findSupportedLocale(NL_BE_VL2));
	}

	@Test
	public void findSupportedLocaleGeeftLocaleTerugMetZelfdeLanguageAlsCountryVerschillendIs() {
		supportedLocales.setLocales(Arrays.asList(NL, NL_BE, NL_BE_VL1, FR_FR, FR_BE, FR));

		assertEquals(NL, supportedLocales.findSupportedLocale(NL_NL));
	}

}
