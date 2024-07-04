package pl.mikus.learning.jobapp.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class LogTimeExecutionAspect {
    @Pointcut("execution(public !static * ((@LogTimeExecution *)+).*(..)) && within(@LogTimeExecution *))")
    public void isClassAnnotated() {}

    @Pointcut("@annotation(LogTimeExecution)")
    public void isMethodAnnotated() {}

    @Around("isClassAnnotated() || isMethodAnnotated()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!log.isDebugEnabled()) return joinPoint.proceed();

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object object = joinPoint.proceed();
        stopWatch.stop();

        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.debug("Execution time of {}#{}() :: {} ms", methodSignature.getDeclaringTypeName(),
                methodSignature.getName(), stopWatch.getTotalTime(TimeUnit.MILLISECONDS));

        stopWatch.prettyPrint();

        return object;
    }
}
