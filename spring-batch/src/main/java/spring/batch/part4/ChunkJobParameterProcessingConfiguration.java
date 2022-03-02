package spring.batch.part4;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class ChunkJobParameterProcessingConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public ChunkJobParameterProcessingConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job chunkJobParameterProcessingJob() {
        return jobBuilderFactory.get("chunkJobParameterProcessingJob")
                .incrementer(new RunIdIncrementer())
                .start(this.chunkStep(null))
                .build();
    }

    @Bean
    @JobScope
    public Step chunkStep(@Value("#{jobParameters[chunkSize]}") String chunkSize) {
        return stepBuilderFactory.get("chunkStep")
                .<String, String>chunk(StringUtils.isNotEmpty(chunkSize) ? Integer.parseInt(chunkSize) : 10)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    private ItemReader<String> itemReader() {
        return new ListItemReader<>(getItem());
    }

    private ItemProcessor<? super String, String> itemProcessor() {
        return item -> item + ", Spring Batch";
    }

    private ItemWriter<? super String> itemWriter() {
        return items -> log.info("chunk item size = {}", items.size());
    }

    private List<String> getItem() {
        List<String> items = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            items.add(i + "Hello");
        }

        return items;
    }
}
