package com.ceerdecy.checkutils.utils;

import com.ceerdecy.checkutils.exception.CheckParamIsNull;
import com.ceerdecy.checkutils.exception.ValueNullException;

import java.lang.reflect.Field;

/**
 * @author CeerDecy
 * @date 2023/2/25 16:37
 */
public class CheckUtils {
    /**
     * Determines whether all properties in this object are empty,throw an exception if there is a null value
     * 判断此对象中的所有属性是否为空，如果存在空值，则抛出异常
     *
     * @param o The object that needs to be judged
     * @return Property values are not empty and return true
     */
    public static boolean checkNull(Object o) {
        if (o == null) throw new CheckParamIsNull("this method param <Object> can't be null ");
        Class<?> c = o.getClass();
        Field[] declaredFields = c.getDeclaredFields();
        for (Field f : declaredFields) {
            f.setAccessible(true);
            try {
                if (f.get(o) == null || f.get(o).equals("")) throw new ValueNullException(" "+c+"'s "+f.getName()+" this value can't be null");
            } catch (IllegalAccessException e) {
                throw new RuntimeException("IllegalAccessException");
            }
        }
        return true;
    }

    /**
     * Determines whether all properties in this object are empty,return false if there is a null value
     * 判断此对象中的所有属性是否为空，如果存在空值，则返回false，否则返回true
     *
     * @param o The object that needs to be judged
     * @return Property values are not empty and return true, otherwise return false
     */
    public static boolean checkNullWithout(Object o) {
        if (o == null) return false;
        Class<?> c = o.getClass();
        Field[] declaredFields = c.getDeclaredFields();
        for (Field f : declaredFields) {
            f.setAccessible(true);
            try {
                if (f.get(o) == null || f.get(o).equals("")) return false;
            } catch (IllegalAccessException e) {
                throw new RuntimeException("IllegalAccessException");
            }
        }
        return true;
    }

    /**
     * Judge whether the attribute with the attribute name in fields in this object is null. If there is a null value, throw an exception
     * 判断此对象中属性名在fields中的属性是否为空，如果存在空值，则抛出异常
     *
     * @param o         The object that needs to be judged
     * @param fields    The names of the attribute to be determined
     * @return          Property values are not empty and return true
     */
    public static boolean checkNull(Object o, String... fields) {
        if (o == null) throw new CheckParamIsNull("this method param <Object> can't be null ");
        Class<?> c = o.getClass();
        for (String field : fields) {
            Field f;
            try {
                f = c.getDeclaredField(field);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException("["+field+"] No Such Field Exception");
            }
            f.setAccessible(true);
            try {
                if (f.get(o) == null || f.get(o).equals("")) throw new ValueNullException(" "+c+"'s "+f.getName()+" this value can't be null");
            } catch (IllegalAccessException e) {
                throw new RuntimeException("IllegalAccessException");
            }
        }
        return true;
    }

    /**
     * Judge whether the attribute with the attribute name in fields in this object is null. If there is a null value, return false
     * 判断此对象中属性名在fields中的属性是否为空，如果存在空值，返回false
     *
     * @param o         The object that needs to be judged
     * @param fields    The names of the attribute to be determined
     * @return          Property values are not empty and return true, otherwise return false
     */
    public static boolean checkNullWithout(Object o, String... fields) {
        if (o == null) return false;
        Class<?> c = o.getClass();
        for (String field : fields) {
            Field f;
            try {
                f = c.getDeclaredField(field);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException("No Such Field Exception");
            }
            f.setAccessible(true);
            try {
                if (f.get(o) == null || f.get(o).equals("")) return false;
            } catch (IllegalAccessException e) {
                throw new RuntimeException("IllegalAccessException");
            }
        }
        return true;
    }

    /**
     * Directly pass in the parameter and judge whether it is null (throw exception)
     * 直接将参数传递进来，并判断其是否为空值
     *
     * @param params    Parameters to be judged
     * @return          Parameters values are not empty and return true, otherwise throw an exception
     */
    public static boolean checkNullOfValue(Object... params) {
        for (Object o : params) {
            if (o == null || o.equals(""))throw new ValueNullException("There is a value of null");
        }
        return true;
    }
    /**
     * Directly pass in the parameter and judge whether it is null
     * 直接将参数传递进来，并判断其是否为空值
     *
     * @param params    Parameters to be judged
     * @return          Parameters values are not empty and return true, otherwise return false
     */
    public static boolean checkNullOfValueWithout(Object... params) {
        for (Object o : params) {
            if (o == null || o.equals(""))return false;
        }
        return true;
    }

    public static boolean checkValue(Object o,String[] params,String[] value){
        if (params.length!=value.length)throw new ValueNullException(o.getClass(),params[0]);
        return true;
    }
}
