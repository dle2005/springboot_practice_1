package hello.aop.implement.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 { /* 포인트컷 분리 */

    @Pointcut("execution(* hello.aop.implement..*(..))")
    private void allOrder() {} // Pointcut signature

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable { // Advice
        log.info("[log] {}", joinPoint.getSignature());

        return joinPoint.proceed();
    }
}
