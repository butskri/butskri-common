package be.butskri.commons.test;

import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class BaseAssertTest {

	private static final String PROPERTY1 = "prop1";
	private static final String PROPERTY2 = "prop2";
	private static final String PROPERTY3 = "prop3";

	@Test
	public void assertContainsAllThrowsNoExceptionIfPropertiesFileToBeCheckedContainsAllPropertiesToBeFound() {
		Properties propertiesToBeFound = new Properties();
		propertiesToBeFound.put(PROPERTY1, "");
		propertiesToBeFound.put(PROPERTY2, "");
		Properties propertiesToBeChecked = new Properties();
		propertiesToBeChecked.put(PROPERTY1, "");
		propertiesToBeChecked.put(PROPERTY2, "");
		propertiesToBeChecked.put(PROPERTY3, "");

		BaseAssert.assertContainsAll(propertiesToBeFound, propertiesToBeChecked);
	}

	@Test(expected = AssertionError.class)
	public void assertContainsAllThrowsAssertionExceptionIfPropertiesFileToBeCheckedDoesNotContainsAllPropertiesToBeFound() {
		Properties propertiesToBeFound = new Properties();
		propertiesToBeFound.put(PROPERTY1, "");
		propertiesToBeFound.put(PROPERTY2, "");
		propertiesToBeFound.put(PROPERTY3, "");
		Properties propertiesToBeChecked = new Properties();
		propertiesToBeChecked.put(PROPERTY1, "");
		propertiesToBeChecked.put(PROPERTY2, "");

		BaseAssert.assertContainsAll(propertiesToBeFound, propertiesToBeChecked);
	}

	@Test(expected = AssertionError.class)
	public void assertBeforeGooitAssertionErrorAlsEersteWaardeGroterDanTweede() {
		BaseAssert.assertBefore(25, 20);
	}

	@Test(expected = AssertionError.class)
	public void assertBeforeGooitAssertionErrorAlsEersteWaardeGelijkAanTweede() {
		BaseAssert.assertBefore(20, 20);
	}

	@Test
	public void assertBeforeGooitGeenExceptionAlsEersteWaardeKleinerDanTweede() {
		BaseAssert.assertBefore(11, 20);
	}

	@Test(expected = AssertionError.class)
	public void assertAfterGooitAssertionErrorAlsEersteWaardeKleinerDanTweede() {
		BaseAssert.assertAfter(20, 25);
	}

	@Test(expected = AssertionError.class)
	public void assertAfterGooitAssertionErrorAlsEersteWaardeGelijkAanTweede() {
		BaseAssert.assertAfter(20, 20);
	}

	@Test
	public void assertAfterGooitGeenExceptionAlsEersteWaardeGroterDanTweede() {
		BaseAssert.assertAfter(30, 20);
	}

	@Test(expected = AssertionError.class)
	public void assertBetweenGooitAsserionErrorAlsWaardeKleinerDanMinimum() {
		BaseAssert.assertBetween(10, 20, 30);
	}

	@Test(expected = AssertionError.class)
	public void assertBetweenGooitAsserionErrorAlsWaardeGroterDanMaximum() {
		BaseAssert.assertBetween(40, 20, 30);
	}

	@Test(expected = AssertionError.class)
	public void assertBetweenGooitAsserionErrorAlsWaardeGelijkAanMinimum() {
		BaseAssert.assertBetween(20, 20, 30);
	}

	@Test(expected = AssertionError.class)
	public void assertBetweenGooitAsserionErrorAlsWaardeGelijkAanMaximum() {
		BaseAssert.assertBetween(30, 20, 30);
	}

	@Test
	public void assertBetweenGooitGeenExceptionAlsWaardeTussenMinimumEnMaximum() {
		BaseAssert.assertBetween(25, 20, 30);
	}
}
