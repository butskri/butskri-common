package org.junit.internal.runners;

import org.junit.rules.ExpectedException;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import be.butskri.commons.test.junit.ExpectedThrowable;

public class ExpectedExceptionRule implements MethodRule {

	@Override
	public Statement apply(Statement base, FrameworkMethod method, Object target) {
		ExpectedThrowable annotation = method.getAnnotation(ExpectedThrowable.class);
		if (annotation != null) {
			ExpectedException delegate = ExpectedException.none();
			delegate.expect(annotation.type());
			delegate.expectMessage(annotation.value());
			return delegate.apply(base, method, target);
			
		}
		return base;
	}

}
