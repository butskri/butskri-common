package be.butskri.commons.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.internal.runners.InitializationError;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.internal.runners.MethodRoadie;
import org.junit.internal.runners.TestMethod;
import org.junit.internal.runners.TestMethodForCheckingExceptionMessage;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.mockito.MockitoAnnotations;

public class JUnit4ButskriClassRunner extends JUnit4ClassRunner {

	public JUnit4ButskriClassRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected TestMethod wrapMethod(Method method) {
		return new TestMethodForCheckingExceptionMessage(method, getTestClass());
	}

	@Override
	protected void invokeTestMethod(Method method, RunNotifier notifier) {
		Description description = methodDescription(method);
		Object test;
		try {
			test = createTest();
		} catch (InvocationTargetException e) {
			notifier.fireTestFailure(new Failure(description, e.getCause()));
			return;
		} catch (Exception e) {
			notifier.fireTestFailure(new Failure(description, e));
			return;
		}
		beforeTestMethod(test, method);
		TestMethod testMethod = wrapMethod(method);
		new MethodRoadie(test, testMethod, notifier, description).run();
	}

	private void beforeTestMethod(Object testObject, Method method) {
		MockitoAnnotations.initMocks(testObject);
//		getTestListener().beforeTestMethod(testObject, method);
	}

//	protected TestListener getTestListener() {
//		return Unitils.getInstance().getTestListener();
//	}
}
