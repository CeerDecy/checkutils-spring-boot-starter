package com.ceerdecy.checkutils.service;

import com.ceerdecy.checkutils.annotation.CheckMap;
import com.ceerdecy.checkutils.exception.CheckAOPException;
import com.ceerdecy.checkutils.utils.CheckUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;

/**
 * @author CeerDecy
 * @date 2023/3/4 15:47
 */
@Aspect
@Slf4j
public class CheckMapService {

    @Pointcut("execution(* *(..))")
    private void pointCut(){}

    @Before("pointCut()&&@annotation(checkMap)")
    public boolean beforeCheckMap(JoinPoint point, CheckMap checkMap){
        Object[] args = point.getArgs();
        MethodSignature signature = (MethodSignature) point.getSignature();
        int index = Arrays.stream(signature.getParameterNames()).toList().indexOf(checkMap.map());
        if (index == -1) throw new CheckAOPException("Can't find this bean: " + checkMap.map());
        try {
            return CheckUtils.checkMap(args[index], checkMap.fields());
        }catch (Exception e){
            throw new CheckAOPException(e,checkMap.map());
        }
    }
}
