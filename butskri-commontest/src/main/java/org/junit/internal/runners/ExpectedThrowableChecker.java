package org.junit.internal.runners;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import be.butskri.commons.test.junit.ExpectedThrowable;

public class ExpectedThrowableChecker extends ThrowableChecker {

	private String expectedMessage;
	private Class<? extends Throwable> type;

	static ThrowableChecker create(Method method) {
		ThrowableChecker result = null;

		ExpectedThrowable expectedThrowable = method.getAnnotation(ExpectedThrowable.class);
		if (expectedThrowable != null) {
			result = new ExpectedThrowableChecker(expectedThrowable);
		}
		return result;
	}

	private ExpectedThrowableChecker(ExpectedThrowable expectedExceptionMessage) {
		expectedMessage = expectedExceptionMessage.value();
		type = expectedExceptionMessage.type();
	}

	@Override
	public void assertNoThrowable() {
		fail("Exception of type " + type + " should have been thrown with message " + expectedMessage);
	}

	@Override
	public boolean assertThrowable(Throwable actual) {
		assertEquals("thrown exception has wrong type", type, actual.getClass());
		assertEquals("wrong errorMessage", expectedMessage, actual.getMessage());
		return true;
	}
}
