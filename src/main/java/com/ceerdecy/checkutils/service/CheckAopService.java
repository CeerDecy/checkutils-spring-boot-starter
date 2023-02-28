package com.ceerdecy.checkutils.service;

import com.ceerdecy.checkutils.annotation.CheckBean;
import com.ceerdecy.checkutils.utils.CheckUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author CeerDecy
 * @date 2023/2/26 00:34
 */
@Aspect
@Component
@Slf4j
public class CheckAopService {
    static {
        log.info("Check Service Starting");
    }
    @Pointcut("execution(* *(..))")
    private void pointCut() {}
    @Before("pointCut() && @annotation(checkBean)")
    public boolean fun(JoinPoint point, CheckBean checkBean){
        Object[] args = point.getArgs();
        MethodSignature signature = (MethodSignature) point.getSignature();
        int index = Arrays.stream(signature.getParameterNames()).toList().indexOf(checkBean.param());
        if(checkBean.fields()[0].equals("")) return CheckUtils.checkNull(args[index]);
        return CheckUtils.checkNull(args[index], checkBean.fields());
    }
}
