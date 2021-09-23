package spring.batch.springbatch.part3;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.sql.In;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class ChunkProcessingConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public ChunkProcessingConfiguration(JobBuilderFactory jobBuilderFactory,
                              StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job chunkProcessingJob() {
        return jobBuilderFactory.get("chunkProcessingJob")
                .incrementer(new RunIdIncrementer())
                .start(this.taskBaseStep())
                .next(this.chunkBaseStep(null)) // JobParameters Spring expression language
                .build();
    }

    @Bean
    @JobScope// JobParameters Spring expression language 사용 방법
    public Step chunkBaseStep(@Value("#{jobParameters[chunkSize]}") String chunkSize) {
        // // Configuration -chunkSize=20 --job.name=chunkProcessingJob
        return stepBuilderFactory.get("chunkBaseStep")
                .<String, String>chunk(StringUtils.isNotEmpty(chunkSize) ? Integer.parseInt(chunkSize) : 10)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    // @Scope는 bran 생성 및 소멸 시킬 지 bean의 lifecycle을 설정
    // @JobScope는 job 실행 시점에 생성/소멸, Step에 선언
    // @StepScope는 Step 실행 시점에 생성/소명, Tasklet, Chunk에 선언
    // @Scope("job") == @JobScope, @Scope("step") == @StepScope
    // Job과 Step 라이프사이클에 의해 생성되기 때문에 Thread safe하게 작동
    // @Value*("{jobParameters[key]}")를 사용하기 위해 @JobScope와 @StepScope는 필수

    private ItemWriter<String> itemWriter() {
        return items -> log.info("chink item size : {}", items.size());
//        return items -> items.forEach(log::info);
    }

    private ItemProcessor<String, String> itemProcessor() {
        return item -> item + ", Spring Batch";
    }

    private ItemReader<String> itemReader() {
        return new ListItemReader<>(getItems());
    }

    @Bean
    public Step taskBaseStep() {
        return stepBuilderFactory.get("taskBaseStep")
                .tasklet(this.tasklet(null))
                .build();
    }

    // 기본적인 tasklet 사용시
//    private Tasklet tasklet() {
//        return ((contribution, chunkContext) -> {
//            List<String> items = getItems();
//            log.info("task item size : {}", items.size());
//
//            return RepeatStatus.FINISHED;
//        });
//    }

    // chunk와 같이 단위로 tasklet 실행하고자 할 때
    @Bean
    @StepScope
    public Tasklet tasklet(@Value("#{jobParameters[chunkSize]}") String value) {
        List<String> items = getItems();

        return ((contribution, chunkContext) -> {
            StepExecution stepExecution = contribution.getStepExecution();

            // JobParameters 객체 사용 방법
//            JobParameters jobParameters = stepExecution.getJobParameters();
            // Configuration -chunkSize=20 --job.name=chunkProcessingJob
//            String value = jobParameters.getString("chunkSize", "10");
            int chunkSize = StringUtils.isNotEmpty(value) ? Integer.parseInt(value) : 10;

            int fromIndex = stepExecution.getReadCount();
            int toIndex = fromIndex + chunkSize;

            if (fromIndex >= items.size()) {
                return RepeatStatus.FINISHED;
            }

            List<String> subList = items.subList(fromIndex, toIndex);

            log.info("task item size : {}", subList.size());

            stepExecution.setReadCount(toIndex);

            return RepeatStatus.CONTINUABLE;
        });
    }

    private List<String> getItems() {
        List<String> items = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            items.add(i + " Hello");
        }

        return items;
    }
}
