package hello.aop.logtrace;

import hello.aop.logtrace.trace.LogTrace;
import hello.aop.logtrace.trace.TraceId;
import hello.aop.logtrace.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final LogTrace trace;

    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus status = null;

        try {
            status = trace.beginSync(traceId, "OrderService.orderItem()");

            orderRepository.save(traceId, itemId);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);

            throw e;
        }
    }
}
