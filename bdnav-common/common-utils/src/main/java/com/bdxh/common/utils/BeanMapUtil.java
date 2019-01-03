package com.bdxh.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanMapUtil {

	private static Logger logger = LoggerFactory.getLogger(BeanMapUtil.class);

	/**
	 * 
	 * mapToObject: 将map转换成对象. <br/>
	 * 
	 * @author TangFangguo
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception
	 */
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) {
		try {
			if (map == null) {
				return null;
			}
			Object obj = beanClass.newInstance();
			BeanUtils.populate(obj, map);
			return obj;
		} catch (Exception e) {
			logger.error("将map转换成对象出现异常: " + e.getLocalizedMessage());
		}
		return null;
	}

	/**
	 * 
	 * objectToMap: 将对象转换成map. <br/>
	 * 
	 * @author TangFangguo
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> objectToMap(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Class<?> clazz = obj.getClass();
			for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
				Field[] declaredFields = clazz.getDeclaredFields();
				for (Field field : declaredFields) {
					field.setAccessible(true);
					map.put(field.getName(), field.get(obj));
				}
			}

		} catch (Exception e) {
			logger.error("将对象转换成map时出现异常: " + e.getLocalizedMessage());
		}
		if (obj == null) {
			return null;
		}
		return map;
	}
	
	/**
	 * 
	 * objectToMap: 将对象转换成map. <br/>
	 * 
	 * @author xuyuan
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static SortedMap<String, String> objectToTreeMap(Object obj) {
		SortedMap<String, String> map = new TreeMap<String, String>();
		try {
			Class<?> clazz = obj.getClass();
			for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
				Field[] declaredFields = clazz.getDeclaredFields();
				for (Field field : declaredFields) {
					field.setAccessible(true);
					String name = field.getName();
					if("serialVersionUID".equals(name)) {
						continue;
					}
					Object value = field.get(obj);
					if (value!=null) {
						map.put(name,String.valueOf(value));
					}
					
				}
			}

		} catch (Exception e) {
			logger.error("将对象转换成map时出现异常: " + e.getLocalizedMessage());
		}
		return map;
	}

	 public static Object mapToObject2(Map<String, Object> map, Class<?> beanClass) throws Exception {    
        if (map == null)   
            return null;
        Object obj = beanClass.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());    
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
        for (PropertyDescriptor property : propertyDescriptors) {  
            Method setter = property.getWriteMethod();    
            if (setter != null) {  
                setter.invoke(obj, map.get(property.getName()));   
            }  
        }
        return obj;  
    }
}
