package spring.batch.part6;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.batch.entity.domain.Person;

import javax.sql.DataSource;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class JdbcCursorReaderConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final DataSource dataSource;

    public JdbcCursorReaderConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, DataSource dataSource) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.dataSource = dataSource;
    }

    @Bean
    public Job jdbcCursorReaderJob() throws Exception {
        return this.jobBuilderFactory.get("jdbcCursorReaderJob")
                .incrementer(new RunIdIncrementer())
                .start(this.jdbcCursorReaderStep())
                .build();
    }

    @Bean
    public Step jdbcCursorReaderStep() throws Exception {
        return this.stepBuilderFactory.get("jdbcCursorReaderStep")
                .<Person, Person>chunk(10)
                .reader(this.jdbcCursorItemReader())
                .writer(itemWriter())
                .build();
    }

    private JdbcCursorItemReader<Person> jdbcCursorItemReader() throws Exception {
        JdbcCursorItemReader<Person> itemReader = new JdbcCursorItemReaderBuilder<Person>()
                .name("jdbcCursorItemReader")
                .dataSource(dataSource)
                .sql("select id, name, age, address from person")
                .rowMapper((rs, rowNum) ->
                        new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4))
                ).build();
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
