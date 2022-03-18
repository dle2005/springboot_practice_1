package hello.aop;

import hello.aop.logtrace.aspect.AopConfig;
import hello.aop.logtrace.trace.LogTrace;
import hello.aop.logtrace.trace.LogTraceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class AopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
	}
}
