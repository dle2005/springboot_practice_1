package spring.batch.part8;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.batch.part5.CustomItemReader;
import spring.batch.entity.domain.Person;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class ItemProcessorConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public ItemProcessorConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job ItemProcessorJob() {
        return this.jobBuilderFactory.get("ItemProcessorJob")
                .incrementer(new RunIdIncrementer())
                .start(this.ItemProcessorStep())
                .build();
    }

    @Bean
    public Step ItemProcessorStep() {
        return this.stepBuilderFactory.get("ItemProcessorStep")
                .<Person, Person>chunk(10)
                .reader(itemRead())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    private ItemReader<Person> itemRead() {
        return new CustomItemReader<>(getItems());
    }

    private ItemProcessor<? super Person, ? extends Person> itemProcessor() {
        return item -> {
            if (item.getId() % 2 == 0) {
                return item;
            }
            return null;
        };
    }

    private ItemWriter<? super Person> itemWriter() {
        return items -> {
            items.forEach(item -> log.info("id : {}", item.getId()));
        };
    }

    private List<Person> getItems() {
        List<Person> items = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            items.add(new Person(i + 1, "name" + i, "age" + i, "address" + i));
        }

        return items;
    }
}
