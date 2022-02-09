package advanced.project.trace;

import java.util.UUID;

public class TraceId {

    private String id; // 트랜잭션 ID
    private int level; // 메서드 호출 깊이

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        // ab99e16f-3cde-4d24-8241-256108c203a2 생성된 UUID 예시
        // ab99e16f 앞 8자리만 사용
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public TraceId createNextId() {
        // 메서드 호출의 깊이 표현 - id는 동일하며 level만 증가하게
        // [ab99e16f] OrderController.request()
        // [ab99e16f] |--> OrderService.orderItem()
        return new TraceId(id, level + 1);
    }

    public TraceId createPreviousId() {
        // 메서드 호출의 깊이 표현 - id는 동일하며 level만 감소하게
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
