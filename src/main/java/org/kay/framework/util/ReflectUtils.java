package org.kay.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.apache.log4j.Logger;

public abstract class ReflectUtils {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(ReflectUtils.class);

	public static Method getMethod(Class<?> clazz, String methodName, Class<?>... methodParameterTypes) {
		Method method = null;
		try {
			method = clazz.getMethod(methodName, methodParameterTypes);
		} catch (Exception e) {
			logger.error(e, e);
		}
		return method;
	}

	public static Object invokeMethod(Method method, Object obj, Object... args) {
		Object retObj = null;
		try {
			retObj = method.invoke(obj, args);
		} catch (Exception e) {
			logger.error(e, e);
		}
		return retObj;
	}

	public static Object getFieldValue(Class<?> clazz, String fieldName, Object instance) {
		Object fieldValue = null;
		try {
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			fieldValue = field.get(instance);
		} catch (Exception e) {
			logger.error(e, e);
		}
		return fieldValue;
	}

	public static <T> T newInstance(Class<T> clazz) {
		T obj = null;
		try {
			obj = clazz.newInstance();
		} catch (Exception e) {
			logger.error(e, e);
		}
		return obj;
	}

}
