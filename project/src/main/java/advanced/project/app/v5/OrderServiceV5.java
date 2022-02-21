package advanced.project.app.v5;

import advanced.project.trace.callback.TraceCallback;
import advanced.project.trace.callback.TraceTemplate;
import advanced.project.trace.logtrace.LogTrace;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepositoryV5;
    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepositoryV5, LogTrace trace) {
        this.orderRepositoryV5 = orderRepositoryV5;
        this.template = new TraceTemplate(trace);
    }

    public void orderItem(String itemId) {
        template.execute("OrderService.orderItem()", (TraceCallback<Void>) () -> {
            orderRepositoryV5.save(itemId);

            return null;
        });
    }
}
