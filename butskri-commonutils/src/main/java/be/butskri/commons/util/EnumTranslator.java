package be.butskri.commons.util;

import org.springframework.context.MessageSource;

public class EnumTranslator {

	private MessageSource messageSource;
	private LanguageBean languageBean;

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void setLanguageBean(LanguageBean languageBean) {
		this.languageBean = languageBean;
	}

	public String translate(Enum<?> enumInstance) {
		String result = null;
		if (enumInstance != null) {
			String messageKey = getMessageKey(enumInstance);
			result = messageSource.getMessage(messageKey, null, languageBean.getLocale());
		}
		return result;
	}

	private String getMessageKey(Enum<?> enumInstance) {
		StringBuffer result = new StringBuffer();
		result.append(enumInstance.getClass().getSimpleName());
		result.append(".");
		result.append(enumInstance.name());
		return result.toString();
	}

}
