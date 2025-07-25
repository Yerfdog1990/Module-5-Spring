package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * Aspect class that handles logging cross-cutting concerns for the Shop class.
 * This aspect provides logging functionality before and after method executions,
 * as well as logging for successful returns and exceptions.
 */
@Component // Registers this aspect as a Spring bean
@Aspect // Marks the class as an aspect, which contains cross-cutting concerns
public class LoggingAspect {
    // Defines a pointcut that matches the execution of any public method in classes under aop.Ishop package

    /**
     * Pointcut that matches all methods in the Shop class.
     * This pointcut is used as a reference by other advices in this aspect.
     */
    @Pointcut("execution(* aop.Shop.*(..))")
    public void allServiceMethods() {
        System.out.println("All method calls.");
    }
    // Advice that runs before the execution of methods matched by the pointcut

    /**
     * Advice that executes before any method in Shop class.
     * Logs the name of the method that is about to be executed.
     */
    @Before("allServiceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before method: " +joinPoint.getSignature().getName());
    }
    // Advice that runs after the execution of methods matched by the pointcut, regardless of their outcome

    /**
     * Advice that executes after any method in Shop class.
     * Logs the name of the method that has been executed.
     */
    @Before("allServiceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("After method: " +joinPoint.getSignature().getName());
    }
    // Advice that runs after a method matched by the pointcut returns successfully

    /**
     * Advice that executes after successful method execution.
     * Logs the method name and its return value.
     */
    @AfterReturning(pointcut = "allServiceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("Method returned from: " +joinPoint.getSignature().getName()+ " result: " +result);
    }
    // Advice that runs if a method matched by the pointcut throws an exception

    /**
     * Advice that executes when a method throws an exception.
     * Logs the method name and the exception details.
     */
    @AfterThrowing(pointcut = "allServiceMethods()", throwing = "throwable")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        System.out.println("Method threw exception: " +joinPoint.getSignature().getName()+ " exception: " +throwable);
    }
    // Advice that runs before and after the execution of methods matched by the pointcut

    /**
     * Around advice that wraps method execution.
     * Provides logging before and after method execution, including return values and exceptions.
     */
    @Around("allServiceMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before method: " +joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();  // Proceed with the next advice or target method invocation
        System.out.println("After method: " +joinPoint.getSignature().getName());
        System.out.println("Method returned from: " +joinPoint.getSignature().getName()+ " result: " +result);
        System.out.println("Method threw exception: " +joinPoint.getSignature().getName()+ " exception: " +result);
        return result;
    }

    /**
     * Specific advice for logging before removeItem method execution.
     * Provides dedicated logging for item removal operations.
     */
    @Before("execution(* aop.Shop.removeItem(..))")
    public void logBeforeRemoveItem(JoinPoint joinPoint) {
        System.out.println("Before remove method: " +joinPoint.getSignature().getName());
    }

    /**
     * Specific advice for logging after removeItem method execution.
     * Provides dedicated logging for completed item removal operations.
     */
    @After("execution(* aop.Shop.removeItem(..))")
    public void logAfterRemoveItem(JoinPoint joinPoint) {
        System.out.println("After remove method: " +joinPoint.getSignature().getName());
    }
}
