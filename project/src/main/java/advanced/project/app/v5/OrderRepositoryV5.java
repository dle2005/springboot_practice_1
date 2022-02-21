package advanced.project.app.v5;

import advanced.project.trace.callback.TraceCallback;
import advanced.project.trace.callback.TraceTemplate;
import advanced.project.trace.logtrace.LogTrace;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {
    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }

    public void save(String itemID) {
        template.execute("OrderRepository.save()", (TraceCallback<Void>) () -> {
            if (itemID.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }

            sleep(1000);

            return null;
        });
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
