package ua.dp.mign;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ua.dp.mign.model.EventType;
import ua.dp.mign.service.EventLogger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan("ua.dp.mign")
@PropertySource(value = "classpath:client.properties")
public class AppConfig {

    @Autowired
    private ApplicationContext ctx;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Map<EventType, EventLogger> typedLoggersMap() {
        HashMap<EventType, EventLogger> map = new HashMap<EventType, EventLogger>();
        map.put(EventType.INFO, ctx.getBean("consoleLogger", EventLogger.class));
        map.put(EventType.ERROR, ctx.getBean("combinedLogger", EventLogger.class));
        return map;
    }

    @Bean
    public List<EventLogger> combinedLoggers() {
        return Lists.newArrayList(ctx.getBean("consoleLogger", EventLogger.class),
                                  ctx.getBean("fileLogger", EventLogger.class));
    }
}
