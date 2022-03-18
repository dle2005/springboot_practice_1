package hello.aop.logtrace;

import hello.aop.logtrace.trace.LogTrace;
import hello.aop.logtrace.trace.LogTraceImpl;
import hello.aop.logtrace.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LogTraceController {

    private final LogTraceService orderService;
    private final LogTrace trace;

    @GetMapping("/request")
    public String request(String itemId) {
        TraceStatus status = null;

        try {
            status = trace.begin("LogTraceController.request()");

            orderService.orderItem(itemId);

            trace.end(status);

            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);

            throw e;
        }
    }
}
