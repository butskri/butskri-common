package be.butskri.commons.util;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

@RunWith(JUnit4ClassRunner.class)
public class ObjectUtilsTest {

	private ObjectUtils objectUtils;

	@Before
	public void setUp() {
		objectUtils = new ObjectUtils();
	}

	@Test
	public void convertToStringForNullReturnsNull() {
		assertNull(objectUtils.convertToString(null));
	}

	@Test
	public void testConvertToStringForPrimitiveReturnsStringRepresentation() {
		assertEquals("<int>30</int>", objectUtils.convertToString(30));
	}

	@Test
	public void testConvertToStringForStringReturnsString() {
		assertEquals("<string>myString</string>", objectUtils.convertToString("myString"));
	}

	@Test
	public void testConvertToStringForBeanReturnsXml() {
		SomeBean someBean = new SomeBean();
		someBean.setIntField(25);
		someBean.setStringField("myString");
		String expected = "<" + SomeBean.class.getName() + ">\n"
				+ "  <stringField>myString</stringField>\n"
				+ "  <intField>25</intField>\n"
				+ "</" + SomeBean.class.getName() + ">";
		assertEquals(expected, objectUtils.convertToString(someBean));
	}
	
	@Test
	public void testConvertToStringForBeanWithEndlessNesting() {
		SomeBean someBean = new SomeBean();
		someBean.setIntField(25);
		someBean.setStringField("myString");
		someBean.setNestedBean(someBean);
		String expected = "<" + SomeBean.class.getName() + ">\n"
				+ "  <stringField>myString</stringField>\n"
				+ "  <intField>25</intField>\n"
				+ "  <nestedBean reference=\"..\"/>\n"
				+ "</" + SomeBean.class.getName() + ">";
		assertEquals(expected, objectUtils.convertToString(someBean));
	}
}
