package spring.batch.UserLevelProject;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import spring.batch.entity.domain.User;
import spring.batch.repository.UserRepository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SaveUserTasklet implements Tasklet {

    private final UserRepository userRepository;

    public SaveUserTasklet(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        List<User> users = createUsers();

        Collections.shuffle(users);

        userRepository.saveAll(users);

        return RepeatStatus.FINISHED;
    }

    private List<User> createUsers() {
        List<User> users = new LinkedList<>();

        for (int i = 0; i < 100; i++) {
            users.add(User.builder()
                    .totalAmount(1_000)
                    .username("user" + i)
                    .build());
        }

        for (int i = 100; i < 200; i++) {
            users.add(User.builder()
                    .totalAmount(200_000)
                    .username("user" + i)
                    .build());
        }

        for (int i = 200; i < 300; i++) {
            users.add(User.builder()
                    .totalAmount(300_000)
                    .username("user" + i)
                    .build());
        }

        for (int i = 300; i < 400; i++) {
            users.add(User.builder()
                    .totalAmount(500_000)
                    .username("user" + i)
                    .build());
        }

        return users;
    }
}
