package be.butskri.commons.context;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;
import org.springframework.context.ApplicationContext;

public class LocatorTest {

	@Mock
	private ApplicationContext applicationContext;

	private Map<String, Object> beanMap;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Locator.getInstance().setApplicationContext(applicationContext);

		beanMap = new HashMap<String, Object>();
		stub(applicationContext.getBeansOfType(Object.class)).toReturn(beanMap);
	}

	@Test(expected = IllegalArgumentException.class)
	public void locateWerptIllegalArgumentExceptionAlsGeenBeanGevondenWordt() {
		Locator.locate(Object.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void locateWerptIllegalArgumentExceptionAlsMeerdereBeansGevondenWorden() {
		beanMap.put("bean1", new Object());
		beanMap.put("bean2", new Object());

		Locator.locate(Object.class);
	}

	@Test
	public void locateGeeftBeanAlsExact1BeanGevondenWordt() {
		Object verwachteBean = new Object();
		beanMap.put("bean1", verwachteBean);

		assertEquals(verwachteBean, Locator.locate(Object.class));
	}
}
