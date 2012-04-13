package be.butskri.commons.test.junit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO remove this
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LocatableByName {

	String name();

}
