package com.freaky.fit.aspects;

import com.freaky.fit.annotations.Authorize;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizationAspect {

    @Around("@annotation(authorize)")
    public Object beforeAdvice(ProceedingJoinPoint joinPoint, Authorize authorize) throws Throwable {

        // TODO: 6/4/21 Here goes the code for authorization of fb
        return joinPoint.proceed();
    }
}
