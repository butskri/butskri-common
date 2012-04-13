package be.butskri.commons.context;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

public class LocatorConfigurator {

	private ApplicationContext oldContext;
	private ApplicationContext stubContext;

	public LocatorConfigurator() {
		oldContext = Locator.getInstance().getApplicationContext();
		stubContext = mock(ApplicationContext.class);
		Locator.getInstance().setApplicationContext(stubContext);
	}

	public <T> void configure(Class<T> type, T bean) {
		Map<String, T> beanMap = new HashMap<String, T>();
		beanMap.put("bean", bean);
		stub(stubContext.getBeansOfType(type)).toReturn(beanMap);
	}

	public void resetContext() {
		Locator.getInstance().setApplicationContext(oldContext);
	}
}
