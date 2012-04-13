package be.butskri.commons.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.math.RoundingMode;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;
import org.springframework.context.MessageSource;

public class EnumTranslatorTest {

	private static final Locale LOCALE = Locales.FR;

	private static final Enum<?> ENUM = RoundingMode.CEILING;
	private static final String ENUM_KEY = "RoundingMode.CEILING";
	private static final String ENUM_VERTALING = "mijnVertaling";

	@Mock
	private MessageSource messageSourceMock;
	@Mock
	private LanguageBean languageBeanMock;

	private EnumTranslator translator;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		translator = new EnumTranslator();
		translator.setMessageSource(messageSourceMock);
		translator.setLanguageBean(languageBeanMock);

		stub(languageBeanMock.getLocale()).toReturn(LOCALE);
		stub(messageSourceMock.getMessage(ENUM_KEY, null, LOCALE)).toReturn(ENUM_VERTALING);
	}

	@Test
	public void translateVertaaltEnum() {
		assertEquals(ENUM_VERTALING, translator.translate(ENUM));
	}

	@Test
	public void translateVertaaltNullNiet() {
		assertNull(translator.translate(null));
	}

}
