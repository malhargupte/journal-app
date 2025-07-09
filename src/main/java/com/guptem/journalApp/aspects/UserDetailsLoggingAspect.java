package com.guptem.journalApp.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserDetailsLoggingAspect {

    @AfterReturning(
            pointcut = "logUserDetailsPointcut()",
            returning = "result"
    )
    public void logUserDetails(JoinPoint joinpoint, Object result) {
        if (result instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User userDetails =
                    (org.springframework.security.core.userdetails.User) result;

            System.out.println("Username: " + userDetails.getUsername());
            System.out.println("Encoded Password: " + userDetails.getPassword());
            System.out.println("Roles: " + userDetails.getAuthorities());

        }
    }

    @Pointcut("execution(* com.guptem.journalApp.services.UserDetailsServiceImpl.loadUserByUsername(..))")
    public void logUserDetailsPointcut() {}

}
















