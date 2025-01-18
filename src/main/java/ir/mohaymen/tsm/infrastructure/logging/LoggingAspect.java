package ir.mohaymen.tsm.infrastructure.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @AfterReturning("execution(* ir.mohaymen.tsm.core.application_services.account.command_handlers.CreateHandler.*(..))")
    public void logAfterAccountCreated(JoinPoint joinPoint) {
        logger.info("A new account created: " + joinPoint.getArgs()[0]);
    }

    @AfterThrowing("execution(* ir.mohaymen.tsm.core.application_services.account.command_handlers.CreateHandler.*(..))")
    public void logCreatingAccountFailed(JoinPoint joinPoint) {
        logger.info("Creating an account failed: " + joinPoint.getArgs()[0]);
    }

    @AfterReturning("execution(* ir.mohaymen.tsm.core.application_services.transaction.command_handlers.CreateHandler.*(..))")
    public void logAfterTransactionCreated(JoinPoint joinPoint) {
        logger.info("A new transaction created: " + joinPoint.getArgs()[0]);
    }

    @AfterThrowing(pointcut = "execution(* ir.mohaymen.tsm.core.application_services.transaction.command_handlers.CreateHandler.*(..))", throwing = "ex")
    public void logTransactionFailed(JoinPoint joinPoint, Throwable ex) {
        logger.log(Level.SEVERE,"A transaction failed due to:" + ex);
    }

    @AfterReturning("execution(* ir.mohaymen.tsm.core.application_services.account.command_handlers.PartialUpdateHandler.*(..))")
    public void logAfterUpdateAccount(JoinPoint joinPoint) {
        logger.info("An account updated: " + joinPoint.getArgs()[0]);
    }

    @AfterThrowing("execution(* ir.mohaymen.tsm.core.application_services.account.command_handlers.PartialUpdateHandler.*(..))")
    public void logUpdateAccountFailed(JoinPoint joinPoint) {
        logger.info("Updating an account failed: " + joinPoint.getArgs()[0]);
    }
}
