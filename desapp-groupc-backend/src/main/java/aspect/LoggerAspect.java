package aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.joda.time.DateTime;

@Aspect
public class LoggerAspect {

    private Logger log = Logger.getLogger(LoggerAspect.class);
    
    @After("execution(*  domain.servicesRest.UserRest*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("AT: " + new DateTime());
        log.info("CLASS: " + joinPoint.getTarget().toString() +
                " | METHOD: " + joinPoint.getSignature().getName() +
                " | ARGUMENTS: " + printArguments(joinPoint.getArgs()));
    }

    private static String printArguments(Object[] anArgumentsArray) {
        Object[] arguments = anArgumentsArray;
        String output = "";
        for (Object argument: arguments) {
            output += argument;
        }
        return output;
    }

}
