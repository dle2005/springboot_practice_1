package advanced.project.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2 { // 필드에 전략을 보관하는 방식

    public void execute(Strategy strategy) {
        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
        strategy.call();

        long endTime = System.currentTimeMillis();
        long resultTIme = endTime - startTime;

        log.info("resultTime={}", resultTIme);
    }
}
