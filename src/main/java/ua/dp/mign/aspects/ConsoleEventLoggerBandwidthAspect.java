package ua.dp.mign.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.dp.mign.model.Event;
import ua.dp.mign.service.EventLogger;

@Component
@Aspect
public class ConsoleEventLoggerBandwidthAspect {
    @Value("3")
    private int bandwith;
    private int count;
    private EventLogger secondaryLogger;

    @Autowired
    @Qualifier("fileLogger")
    public void setSecondaryLogger(EventLogger secondaryLogger) {
        this.secondaryLogger = secondaryLogger;
    }

    @Pointcut("execution(* *..Console*.logEvent(..))")
    private void consoleEventLogging() {}

    @Around("consoleEventLogging() && args(evt)")
    public Object limitBandwidth(ProceedingJoinPoint jp, Object evt) throws Throwable {
        Object result = null;
        if (count < bandwith) {
            result = jp.proceed(new Object[] {evt});
        } else {
            secondaryLogger.logEvent((Event)evt);
        }
        count++;
        return result;
    }
}
