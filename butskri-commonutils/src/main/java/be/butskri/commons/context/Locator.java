package be.butskri.commons.context;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Locator implements ApplicationContextAware {

	private static Locator singleton = new Locator();
	private ApplicationContext applicationContext;

	public static Locator getInstance() {
		return singleton;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public static <T> T locate(Class<T> clazz) {
		return getInstance().locateIt(clazz);
	}

	@SuppressWarnings("unchecked")
	protected <T> T locateIt(Class<T> clazz) {
		Map<String, T> beansOfType = applicationContext.getBeansOfType(clazz);

		if (beansOfType.size() != 1) {
			throw new IllegalArgumentException("invalid number of beans found for class " + clazz + ": "
					+ beansOfType.size());
		}

		return beansOfType.values().iterator().next();
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

}
