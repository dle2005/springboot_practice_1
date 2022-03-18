package hello.aop.logtrace.aspect;

import hello.aop.logtrace.trace.LogTrace;
import hello.aop.logtrace.trace.LogTraceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfig {

    @Bean
    public LogTraceAspect logTraceAspect(LogTrace logTrace) {
        return new LogTraceAspect(logTrace);
    }


    @Bean
    public LogTrace logTrace() {
        return new LogTraceImpl();
    }
}
