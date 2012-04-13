package org.junit.internal.runners;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CompositeExceptionChecker extends ThrowableChecker {

	private List<ThrowableChecker> checkers = new ArrayList<ThrowableChecker>();

	static ThrowableChecker create(Method method) {
		CompositeExceptionChecker result = new CompositeExceptionChecker();
		result.addChecker(ExpectedThrowableChecker.create(method));
		return result;
	}

	@Override
	public boolean assertThrowable(Throwable throwable) {
		boolean result = false;
		for (ThrowableChecker checker : checkers) {
			result = checker.assertThrowable(throwable) || result;
		}
		return result;
	}

	@Override
	public void assertNoThrowable() {
		for (ThrowableChecker checker : checkers) {
			checker.assertNoThrowable();
		}
	}

	public void addChecker(ThrowableChecker checker) {
		if (checker != null) {
			checkers.add(checker);
		}
	}

}
