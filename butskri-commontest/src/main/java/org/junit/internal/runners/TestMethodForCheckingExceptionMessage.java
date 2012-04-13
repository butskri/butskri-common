package org.junit.internal.runners;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Ignore;

@Ignore
public class TestMethodForCheckingExceptionMessage extends TestMethod {

	private ThrowableChecker throwableChecker;

	public TestMethodForCheckingExceptionMessage(Method method, TestClass testClass) {
		super(method, testClass);
		throwableChecker = CompositeExceptionChecker.create(method);
	}

	@Override
	public void invoke(Object test) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		try {
			super.invoke(test);
			throwableChecker.assertNoThrowable();
		} catch (InvocationTargetException e) {
			Throwable actual = e.getTargetException();
			if (!throwableChecker.assertThrowable(actual)) {
				throw e;
			}
		}
	}

}
