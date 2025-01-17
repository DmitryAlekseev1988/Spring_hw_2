package ru.gb.timesheet.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j // Slf4j - Simple logging facade for java
@Aspect
@Component
public class LoggingAspect {

    // Before
    // AfterThrowing
    // AfterReturning
    // After = AfterReturning + AfterThrowing
    // Around ->

//  Bean = TimesheetService, obj = timesheetService
    // proxyTimesheetService(obj)

    @Pointcut("execution(* ru.gb.timesheet.service.TimesheetService.*(..))")
    public void timesheetServiceMethodsPointcut() {
    }

    // Pointcut - точка входа в аспект
    @Before(value = "timesheetServiceMethodsPointcut()")
    public void beforeTimesheetServiceFindById(JoinPoint jp) {
        String methodName = jp.getSignature().getName();

        StringBuilder signature = new StringBuilder("Args:");
        Object[] signatureArgs = jp.getArgs();
        for (Object signatureArg : signatureArgs) {
            String typeName = signatureArg.getClass().getSimpleName();
            signature.append(" (").append(typeName).append(" = ")
                    .append(signatureArg).append(")");
        }

        log.info("Before -> TimesheetService#{} " + signature, methodName);
    }

//  @AfterThrowing(value = "timesheetServiceMethodsPointcut()", throwing = "ex")
//  public void afterTimesheetServiceFindById(JoinPoint jp, Exception ex) {
//    String methodName = jp.getSignature().getName();
//    log.info("AfterThrowing -> TimesheetService#{} -> {}", methodName, ex.getClass().getName());
//  }

}
