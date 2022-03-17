package hello.aop.logtrace.trace;

import org.junit.jupiter.api.Test;

class LogTraceTest {

    @Test
    void begin_end() {
        LogTrace trace = new LogTrace();

        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    @Test
    void begin_exception() {
        LogTrace trace = new LogTrace();

        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
    }
}