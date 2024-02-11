package fr.charisma.bvj.rc_bvj.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BatchLoggingAspect {

	private static final Logger logger = LoggerFactory.getLogger(BatchLoggingAspect.class);

    @Before("execution(* your.package.name.MyJobScheduler.performJob(..))")
    public void logBeforeJobExecution(JoinPoint joinPoint) {
        // Log job start using SLF4J
        logger.info("Job started at: {}", System.currentTimeMillis());
    }

    @After("execution(* your.package.name.MyJobScheduler.performJob(..))")
    public void logAfterJobExecution(JoinPoint joinPoint) {
        // Log job end using SLF4J
        logger.info("Job ended at: {}", System.currentTimeMillis());
    }
}
