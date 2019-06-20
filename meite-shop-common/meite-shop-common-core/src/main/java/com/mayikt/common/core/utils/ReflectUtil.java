package com.mayikt.common.core.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectUtil {
    
    /**
     * 根据泛型类的已经声明泛型类型的子类获取指定位置的泛型
     * @author 北北
     * @date 2018年1月18日上午11:34:59
     * @param clazz -- 当前类
     * @param index -- 第几个泛型, 从0开始
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getGenericClass(Class<T> clazz, Integer index) {
        if(clazz == null){
            return null;
        }
        if(index == null){
            index = 0;
        }
        
        //提取泛型类数组
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass(); 
        Type[] genericTypes = parameterizedType.getActualTypeArguments();
        
        //越界判断
        if((index+1) > genericTypes.length){
            return null;
        }
        Class<T> retClass= (Class<T>) genericTypes[index];
        return retClass; 
    }


}