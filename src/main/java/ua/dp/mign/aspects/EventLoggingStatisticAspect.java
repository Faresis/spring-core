package ua.dp.mign.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class EventLoggingStatisticAspect {

    private final Map<Class, Integer> counter = new HashMap<Class, Integer>();

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {}

    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint jp) {
        Class<?> target = jp.getTarget().getClass();
        Integer count = counter.get(target);
        Integer increased = count == null ? 1 : count + 1;
        counter.put(target, increased);
    }

    @PreDestroy
    public void print() {
        System.out.println(counter);
    }
}
