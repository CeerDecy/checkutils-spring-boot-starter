package com.ceerdecy.checkutils.exception;

/**
 * @author CeerDecy
 * @date 2023/3/2 10:37
 */
public class CheckAOPException extends RuntimeException{
    public static final String CHECK_AOP_EXCEPTION = "CheckAopException";

    public CheckAOPException(String message) {
        super("["+CHECK_AOP_EXCEPTION+"]-"+message);
    }
}
