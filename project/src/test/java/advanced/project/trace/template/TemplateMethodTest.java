package advanced.project.trace.template;

import advanced.project.trace.template.code.AbstractTemplate;
import advanced.project.trace.template.code.SubClassLogic1;
import advanced.project.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");

        long endTime = System.currentTimeMillis();
        long resultTIme = endTime - startTime;

        log.info("resultTime={}", resultTIme);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");

        long endTime = System.currentTimeMillis();
        long resultTIme = endTime - startTime;

        log.info("resultTime={}", resultTIme);
    }

    @Test
    void templateMethodV1() { // 템플릿 메서드 패턴 적용
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }

    // V1의 템플릿 메서드 패턴은 비즈니스 로직마다 계속 클래스를 만들어야 하는 문제가 존재.
    @Test
    void templateMethodV2() {
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직1 실행");
            }
        };

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직2 실행");
            }
        };

        template1.execute();
        template2.execute();
    }
}
