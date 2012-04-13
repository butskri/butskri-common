package be.butskri.commons.util;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

import java.util.Locale;

import org.apache.commons.lang.LocaleUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;

import be.butskri.commons.test.JUnit4ButskriClassRunner;

@RunWith(JUnit4ButskriClassRunner.class)
public class LanguageBeanTest {

	private static final Locale NL_BE = LocaleUtils.toLocale("nl_BE");
	private static final Locale NL = LocaleUtils.toLocale("nl");
	private static final Locale FR_BE = LocaleUtils.toLocale("fr_BE");
	private static final Locale FR = LocaleUtils.toLocale("fr");

	@Mock
	private LocaleResolver localeResolverMock;
	@Mock
	private SupportedLocales supportedLocalesMock;

	private LanguageBean languageBean;

	@Test
	@Ignore
	public void getLocaleGeeftSupportedLocaleTerug() {
		assertEquals(NL, languageBean.getLocale());

		verify(localeResolverMock).setLocale(NL);
	}

	@Test
	@Ignore
	public void setLocaleWijzigtLocaleNaarSupportedLocale() {
		languageBean.setLocale(FR_BE);

		assertEquals(FR, languageBean.getLocale());
		verify(localeResolverMock).setLocale(FR);
	}

	@Before
	public void setUp() {
		languageBean = new LanguageBean();
		languageBean.setLocaleResolver(localeResolverMock);
		languageBean.setSupportedLocales(supportedLocalesMock);

		stub(localeResolverMock.getLocale()).toReturn(NL_BE);
		stub(supportedLocalesMock.findSupportedLocale(NL_BE)).toReturn(NL);
		stub(supportedLocalesMock.findSupportedLocale(FR_BE)).toReturn(FR);
	}
}
