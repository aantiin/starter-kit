package com.antin.kit.common.config;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class AspectConfig {
    public static final Logger LOGGER = LoggerFactory.getLogger(AspectConfig.class);

    @Around("execution(* com.antin.kit.*.controller..*.*(..)) && !execution(* com.antin.kit.common.controller.PingController" +
            ".*(..))")
    public Object logTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            LOGGER.info("***** Starting: " + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + " *****");
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            LOGGER.info("***** Completed: " + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + " execution time : " + stopWatch.getTotalTimeMillis() + " ms *****");
        }
    }
}
