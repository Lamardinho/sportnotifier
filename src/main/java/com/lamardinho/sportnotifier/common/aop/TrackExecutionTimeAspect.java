package com.lamardinho.sportnotifier.common.aop;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static java.lang.System.currentTimeMillis;

@Aspect
@Component
@Log4j2
public class TrackExecutionTimeAspect {

    @Around("@annotation(com.lamardinho.sportnotifier.common.aop.TrackExecutionTime)")
    public Object executionTime(@NonNull ProceedingJoinPoint point) throws Throwable {
        val startTime = currentTimeMillis();
        val object = point.proceed();
        log.info(
                "{}. Track execution time: {}ms. By: {}",
                point.getSignature(),
                (currentTimeMillis() - startTime),
                getUserName()
        );
        return object;
    }

    private String getUserName() {
        val securityContext = SecurityContextHolder.getContext();
        if (securityContext == null || securityContext.getAuthentication() == null) {
            return "anonymous";
        }
        return securityContext.getAuthentication().getName();
    }
}

