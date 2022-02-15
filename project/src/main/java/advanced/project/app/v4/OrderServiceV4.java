package advanced.project.app.v4;

import advanced.project.trace.TraceStatus;
import advanced.project.trace.logtrace.LogTrace;
import advanced.project.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepositoryV4;
    private final LogTrace trace;

    public void orderItem(String itemId) {
        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                orderRepositoryV4.save(itemId);

                return null;
            }
        };

        template.execute("OrderService.orderItem()");
    }
}
