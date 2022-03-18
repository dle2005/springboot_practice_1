package hello.aop.logtrace.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LogTraceControllerV2 {

    private final LogTraceServiceV2 orderService;

    @GetMapping("/v2/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);

        return "ok";
    }
}
