package advanced.project.app.v3;

import advanced.project.trace.TraceId;
import advanced.project.trace.TraceStatus;
import advanced.project.trace.hellotrace.HelloTraceV2;
import advanced.project.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepositoryV3;
    private final LogTrace trace;

    public void orderItem(String itemId) {
        TraceStatus status = null;

        try {
            status = trace.begin("OrderService.orderItem()");

            orderRepositoryV3.save(itemId);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);

            throw e;
        }
    }
}
