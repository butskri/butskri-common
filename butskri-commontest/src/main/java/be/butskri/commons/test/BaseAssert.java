package be.butskri.commons.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;

public class BaseAssert extends Assert {

	public static <T> void assertIsInstance(Class<? extends T> clazz, T instance) {
		assertTrue(clazz.isInstance(instance));
	}

	public static <T> void assertNotContains(Collection<? extends T> collection, T... expectedElements) {
		for (T expectedElement : expectedElements) {
			assertFalse("ExpectedElement = " + expectedElement + " found in actual = " + collection, collection
					.contains(expectedElement));
		}
	}

	public static <T> void assertContains(Collection<? extends T> collection, T... expectedElements) {
		for (T expectedElement : expectedElements) {
			assertTrue("ExpectedElement = " + expectedElement + " not found in actual = " + collection, collection
					.contains(expectedElement));
		}
	}

	public static <T> void assertContainsOnly(Collection<? extends T> collection, T... expectedElements) {
		assertContains(collection, expectedElements);
		assertSize(expectedElements.length, collection);
	}

	public static void assertContainsAll(Collection<?> expected, Collection<?> found) {
		assertSize(expected.size(), found);
		assertTrue(expected.containsAll(found));
	}

	public static void assertEmpty(Collection<?> actual) {
		assertSize(0, actual);
	}

	public static void assertSize(int expectedSize, Collection<?> actual) {
		assertSize(null, expectedSize, actual);
	}

	public static <T extends Object> void assertSize(int expectedSize, T[] actual) {
		assertEquals(expectedSize, actual.length);
	}

	public static void assertSize(String message, int expectedSize, Collection<?> actual) {
		assertEquals(message, expectedSize, actual.size());
	}

	public static void assertNoClassesFound(String msg, Collection<Class<?>> classesHavingEnumFields) {
		assertSize(msg + "\n" + formatClassNamesAsString(classesHavingEnumFields), 0, classesHavingEnumFields);
	}

	public static void assertContainsAll(Properties propertiesToBeFound, Properties propertiesToBeChecked) {
		List<Object> missingProperties = new ArrayList<Object>();
		Enumeration<Object> keys = propertiesToBeFound.keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();

			if (!propertiesToBeChecked.containsKey(key)) {
				missingProperties.add(key);
			}
		}

		assertSize("missing properties:\n " + StringUtils.join(missingProperties, "\n "), 0, missingProperties);
	}

	public static <T extends Comparable<T>> void assertBefore(T comp1, T comp2) {
		assertTrue(comp1 + " should be before " + comp2, comp1.compareTo(comp2) < 0);
	}

	public static <T extends Comparable<T>> void assertAfter(T comp1, T comp2) {
		assertTrue(comp1 + " should be after " + comp2, comp1.compareTo(comp2) > 0);
	}

	public static <T extends Comparable<T>> void assertBetween(T comp, T min, T max) {
		assertAfter(comp, min);
		assertBefore(comp, max);
	}

	private static String formatClassNamesAsString(Collection<Class<?>> classesHavingEnumFields) {
		String result = "";
		for (Class<?> clazz : classesHavingEnumFields) {
			result += " at " + clazz.getName() + ".unknownCode(" + clazz.getSimpleName() + ".java:1)\n";
		}
		return result;
	}

}
