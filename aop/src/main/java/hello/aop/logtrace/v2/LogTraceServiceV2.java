package hello.aop.logtrace.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogTraceServiceV2 {

    private final LogTraceRepositoryV2 logTraceRepository;

    public void orderItem(String itemId) {
        logTraceRepository.save(itemId);
    }
}
