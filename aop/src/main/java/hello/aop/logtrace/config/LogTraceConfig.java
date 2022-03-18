package hello.aop.logtrace.config;

import hello.aop.logtrace.trace.LogTrace;
import hello.aop.logtrace.trace.LogTraceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new LogTraceImpl();
    }
}
