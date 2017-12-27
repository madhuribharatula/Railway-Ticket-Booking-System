package com.cmpe275.cusrTicketBooking.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private Logger logger = Logger.getLogger(getClass().getName());
	 
    @Before("execution(* com.cmpe275.cusrTicketBooking.service.*.*(..))")
    public void callSetters(JoinPoint joinPoint) {
        logger.info("Service Called");
        logger.info("Method Invoked: " + joinPoint.getSignature().getName());
    }
}
