package be.butskri.commons.test.reflection;

import java.lang.reflect.Field;

public class ReflectionUtils {

	@SuppressWarnings("unchecked")
	public static <T> T getFieldValue(Object obj, String fieldName) {
		T result = null;

		try {
			Field field = getDeclaredField(obj.getClass(), fieldName);
			field.setAccessible(true);
			result = (T) field.get(obj);
		} catch (Exception e) {
			Class<?> clazz = null;
			if (obj != null) {
				clazz = obj.getClass();
			}
			throw new RuntimeException("error getting field " + fieldName + " of class " + clazz, e);
		}

		return result;
	}

	public static void setFieldValue(Object obj, String fieldName, Object fieldValue) {
		try {
			Field field = getDeclaredField(obj.getClass(), fieldName);
			field.setAccessible(true);
			field.set(obj, fieldValue);
		} catch (Exception e) {
			Class<?> clazz = null;
			if (obj != null) {
				clazz = obj.getClass();
			}
			throw new RuntimeException("error setting value " + fieldValue + " for field " + fieldName + " of class "
					+ clazz, e);
		}
	}

	public static Field getDeclaredField(Class<?> clazz, String fieldName) {
		Field result = null;

		if (clazz == null) {
			throw new RuntimeException("error finding field " + fieldName + " of class " + clazz);
		} else {
			try {
				result = clazz.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				result = getDeclaredField(clazz.getSuperclass(), fieldName);
			}
		}

		return result;
	}

}
