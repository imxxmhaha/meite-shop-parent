package com.mayikt.common.core.utils;

import org.springframework.beans.BeanUtils;

public class MiteBeanUtils {

	/**
	 * dot 转换为Do 工具类
	 * 
	 * @param eObject
	 * @param tClass
	 * @return
	 */
	public static <T> T E2T(Object eObject, Class<T> tClass) {
		// 判断dto是否为空!
		if (eObject == null) {
			return null;
		}
		// 判断DoClass 是否为空
		if (tClass == null) {
			return null;
		}
		try {
			T newInstance = tClass.newInstance();
			BeanUtils.copyProperties(eObject, newInstance);
			// Dto转换Do
			return newInstance;
		} catch (Exception e) {
			return null;
		}
	}

}