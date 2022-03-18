package hello.aop.logtrace;

import hello.aop.logtrace.trace.LogTrace;
import hello.aop.logtrace.trace.LogTraceImpl;
import hello.aop.logtrace.trace.TraceId;
import hello.aop.logtrace.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LogTraceRepository {

    private final LogTrace trace;

    public void save(String itemID) {
        TraceStatus status = null;

        try {
            status = trace.begin("LogTraceRepository.save()");

            if (itemID.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }

            sleep(1000);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);

            throw e;
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
