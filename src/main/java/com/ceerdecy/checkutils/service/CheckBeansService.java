package com.ceerdecy.checkutils.service;

import com.ceerdecy.checkutils.annotation.CheckBean;
import com.ceerdecy.checkutils.annotation.CheckBeans;
import com.ceerdecy.checkutils.exception.CheckAOPException;
import com.ceerdecy.checkutils.utils.CheckUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;

/**
 * @author CeerDecy
 * @date 2023/2/26 00:34
 */
@Aspect
@Slf4j
public class CheckBeansService {
    static {
        log.info("Check Service Starting");
    }

    @Pointcut("execution(* *(..))")
    private void pointCut() {}
    @Before("pointCut() && @annotation(checkBean)")
    public boolean beforeCheckBean(JoinPoint point, CheckBean checkBean) {
        boolean flag = true;
        Object[] args = point.getArgs();
        MethodSignature signature = (MethodSignature) point.getSignature();
        if (checkBean.bean().equals("")) {
            for (Object arg : args) {
                flag = getFlag(signature.getParameterNames()[0], checkBean.fields(), arg);
            }
        } else {
            int index = Arrays.stream(signature.getParameterNames()).toList().indexOf(checkBean.bean());
            if (index == -1) throw new CheckAOPException("Can't find this bean: " + checkBean.bean());
            flag = getFlag(checkBean.bean(), checkBean.fields(), args[index]);
        }
        return flag;
    }

    @Before("pointCut() && @annotation(checkBeans)")
    public boolean beforeCheckBeans(JoinPoint point, CheckBeans checkBeans) {
        Object[] args = point.getArgs();
        boolean flag = true;
        if (checkBeans.value().length > args.length)
            throw new CheckAOPException("The number of annotations does not match the number of parameters");
        MethodSignature signature = (MethodSignature) point.getSignature();
        for (int i = 0; i < checkBeans.value().length; i++) {
            CheckBean checkBean = checkBeans.value()[i];
            if (checkBean.bean().equals("")) {
                flag = getFlag(signature.getParameterNames()[i], checkBean.fields(), args[i]);
            } else {
                int index = Arrays.stream(signature.getParameterNames()).toList().indexOf(checkBean.bean());
                if (index == -1) throw new CheckAOPException("Can't find this bean: " + checkBean.bean());
                flag = getFlag(checkBean.bean(), checkBean.fields(), args[index]);
            }
        }
        return flag;
    }


    private boolean getFlag(String beanName, String[] fields, Object o) {
        try {
            return fields[0].equals("") ? CheckUtils.checkNull(o) : CheckUtils.checkNull(o, fields);
        } catch (Exception e) {
            throw new CheckAOPException(e.getMessage() + " in param [" + beanName + "]");
        }
    }
}
