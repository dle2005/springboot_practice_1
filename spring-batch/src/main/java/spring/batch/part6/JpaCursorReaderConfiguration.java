package spring.batch.part6;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.batch.part5.Person;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class JpaCursorReaderConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final EntityManagerFactory entityManagerFactory;

    public JpaCursorReaderConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, EntityManagerFactory entityManagerFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public Job jpaCursorReaderJob() throws Exception {
        return this.jobBuilderFactory.get("jpaCursorReaderJob")
                .incrementer(new RunIdIncrementer())
                .start(this.jpaCursorReaderStep())
                .build();
    }

    @Bean
    public Step jpaCursorReaderStep() throws Exception {
        return this.stepBuilderFactory.get("jpaCursorReaderStep")
                .<Person, Person>chunk(10)
                .reader(this.jpaCursorItemReader())
                .writer(itemWriter())
                .build();
    }

    private JpaCursorItemReader<Person> jpaCursorItemReader() throws Exception {
        JpaCursorItemReader<Person> itemReader = new JpaCursorItemReaderBuilder<Person>()
                .name("jpaCursorItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select p from Person p")
                .build();
        itemReader.afterPropertiesSet();

        return itemReader;
    }


    private ItemWriter<? super Person> itemWriter() {
        return items -> log.info(items.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", "))
        );
    }
}
