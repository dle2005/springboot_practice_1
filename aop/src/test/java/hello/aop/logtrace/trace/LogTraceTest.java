package hello.aop.logtrace.trace;

import org.junit.jupiter.api.Test;

class LogTraceTest {

    @Test
    void begin_end() {
        LogTrace trace = new LogTrace();

        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");

        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception() {
        LogTrace trace = new LogTrace();

        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");

        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}