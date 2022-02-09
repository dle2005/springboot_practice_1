package advanced.project.trace;

public class TraceStatus { // 로그의 상태 정보

    private TraceId traceId; // 트랜잭션 ID, level
    private Long startTimeMs; // 로그 시작시간
    private String message; // 로그 시작시 사용한 메세지

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public String getMessage() {
        return message;
    }
}
