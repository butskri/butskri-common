package org.junit.internal.runners;

import java.lang.reflect.Method;

public abstract class ThrowableChecker {

	static ThrowableChecker create(Method method) {
		return ExpectedThrowableChecker.create(method);
	}

	public abstract void assertNoThrowable();

	// true indien exception ge-assert is
	public abstract boolean assertThrowable(Throwable throwable);

}
