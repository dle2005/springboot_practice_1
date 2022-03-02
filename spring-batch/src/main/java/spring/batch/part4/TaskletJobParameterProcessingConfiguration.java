package spring.batch.part4;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class TaskletJobParameterProcessingConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public TaskletJobParameterProcessingConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job taskJobParameterProcessingJob() {
        return jobBuilderFactory.get("taskJobParameterProcessingJob")
                .incrementer(new RunIdIncrementer())
                .start(this.taskStep())
                .build();
    }

    @Bean
    public Step taskStep() {
        return stepBuilderFactory.get("taskStep")
                .tasklet(this.tasklet())
                .build();
    }

    private Tasklet tasklet() {
        List<String> items = getItem();

        return ((contribution, chunkContext) -> {
            StepExecution stepExecution = contribution.getStepExecution();
            JobParameters jobParameters = stepExecution.getJobParameters();

            String value = jobParameters.getString("chunkSize", "10");
            int chunkSize = StringUtils.isNotEmpty(value) ? Integer.parseInt(value) : 0;
            int fromIndex = stepExecution.getReadCount();
            int toIndex = fromIndex + chunkSize;

            if (fromIndex >= items.size()) {
                return RepeatStatus.FINISHED;
            }

            List<String> subList = items.subList(fromIndex, toIndex);

            log.info("task item size = {}", subList.size());

            stepExecution.setReadCount(toIndex);

            return RepeatStatus.CONTINUABLE;
        });
    }

    private List<String> getItem() {
        List<String> items = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            items.add(i + "Hello");
        }

        return items;
    }
}
