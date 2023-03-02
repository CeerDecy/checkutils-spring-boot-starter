package com.ceerdecy.checkutils.exception;

/**
 * @author CeerDecy
 * @date 2023/2/25 18:09
 */
public class ValueNullException extends RuntimeException{
    public ValueNullException(String e) {
        super(e);
    }
    public ValueNullException(Class<?> c,String name) {
        super("["+c+"."+name+"] this value can't be null");
    }
}
