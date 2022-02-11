package advanced.project.app.v3;

import advanced.project.trace.TraceId;
import advanced.project.trace.TraceStatus;
import advanced.project.trace.hellotrace.HelloTraceV2;
import advanced.project.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {
    private final LogTrace trace;

    public void save(String itemID) {
        TraceStatus status = null;

        try {
            status = trace.begin("OrderRepository.save()");

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
