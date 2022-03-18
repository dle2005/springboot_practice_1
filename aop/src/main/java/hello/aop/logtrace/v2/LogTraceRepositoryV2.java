package hello.aop.logtrace.v2;

import hello.aop.logtrace.trace.LogTrace;
import hello.aop.logtrace.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LogTraceRepositoryV2 {

    public void save(String itemID) {
        if (itemID.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }

        sleep(1000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
