package spring.batch.springbatch.part1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class HelloConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public HelloConfiguration(JobBuilderFactory jobBuilderFactory,
                              StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    // Spring Batch 에서 Job은 실행 단위
    // Job을 만들기 위해 Batch에서 JobBuilderFactory class를 제공
    // JobBuilderFactory는 Batch에서 Bean으로 생성되어 있어 생성자 주입 사용 가능

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")
                .incrementer(new RunIdIncrementer())
                .start(this.helloStep())
                .build();
    }

    // RunIdIncrementer는 Job이 실행될 떄마다 Parameter Id를 자동으로 생성
    // name(helloJob)은 Spring Batch를 실행될 수 있는 키이기도 함
    // start()는 Job이 실행될 때 최초로 실행될 step을 설정

    @Bean
    public Step helloStep() {
        return stepBuilderFactory.get("helloStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("hello spring batch");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    // step은 Job의 실행단위 하나의 Job은 하나 이상의 step을 가질 수 있음
    // tasklet= step의 실행 단위

}
