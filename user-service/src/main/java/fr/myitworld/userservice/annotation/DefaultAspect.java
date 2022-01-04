package fr.myitworld.userservice.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class DefaultAspect {

    @Value("${information.activated}")
    private boolean informationActivated;

    @Around("execution(private String message*(*))")
    public String getInformationAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (informationActivated) {
            return "Hellooooooo";
        }
        return (String) proceedingJoinPoint.proceed();
    }
}
