package spring.batch.part7;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import spring.batch.part5.CustomItemReader;
import spring.batch.part5.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class JpaItemWriterConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final EntityManagerFactory entityManagerFactory;

    public JpaItemWriterConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, EntityManagerFactory entityManagerFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public Job jpaItemWriterJob() throws Exception {
        return this.jobBuilderFactory.get("jpaItemWriterJob")
                .incrementer(new RunIdIncrementer())
                .start(this.jpaItemWriterStep())
                .build();
    }

    @Bean
    public Step jpaItemWriterStep() throws Exception {
        return this.stepBuilderFactory.get("jpaItemWriterStep")
                .<Person, Person>chunk(10)
                .reader(itemReader())
                .writer(itemWriter())
                .build();
    }

    /*
      별다른 설정을 하지 않으면 EntityManager 의 merge method 가 실행
      merge 는 entity 가 수정대상인지 확인하기 위해 select 를 실행
      수정 대상이면 update, 저장 대상이면 insert 를 실행

      select 하지 않을시 userPersist(true) 설정
        id 를 생성하여 할당시 merge method 는 이미 있는 entity 로 인식
     */

    private ItemWriter<Person> itemWriter() throws Exception {
        JpaItemWriter<Person> itemWriter = new JpaItemWriterBuilder<Person>()
                .entityManagerFactory(entityManagerFactory)
//                .usePersist(true)
                .build();
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }


    private ItemReader<Person> itemReader() {
        return new CustomItemReader<>(getItems());
    }

    private List<Person> getItems() {
        List<Person> items = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            items.add(new Person(i + 1, "name" + i, "age" + i, "address" + i));
        }

        return items;
    }
}
