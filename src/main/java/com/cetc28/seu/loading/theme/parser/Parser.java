package com.cetc28.seu.loading.theme.parser;

import java.util.List;

/**
 * Description : the base class of parse theme
 * 
 * @author yaosheng
 *
 */
public class Parser {

	private static Class<?>[] BaseClassArray = { String.class, Integer.class, Boolean.class, Double.class, Long.class,
			Float.class, Byte.class, Short.class, Character.class };//判断类是否为基础类型

	public boolean isList(Class<?> type) {
		if (type == List.class) {
			return true;
		}
		return false;
	}

	public boolean isObject(Class<?> type) {

		if (type.isPrimitive()) {
			return false;
		}
		for (Class<?> baseType : BaseClassArray) {
			if (type == baseType) {
				return false;
			}
		}
		return true;
	}

	public boolean isBaseArray(Class<?> type) {
		boolean isBaseArray = false;
		Class<?> arrayType = type.getComponentType();
		if (!isObject(arrayType)) {
			isBaseArray = true;
		}
		return isBaseArray;
	}

	public boolean isArray(Class<?> type) {

		if (type.isArray()) {
			return true;
		}
		return false;
	}
}
