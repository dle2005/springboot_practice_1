//package hello.aop.logtrace.v1;
//
//import hello.aop.logtrace.trace.LogTrace;
//import hello.aop.logtrace.trace.TraceStatus;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class LogTraceServiceV1 {
//
//    private final LogTraceRepositoryV1 logTraceRepository;
//    private final LogTrace trace;
//
//    public void orderItem(String itemId) {
//        TraceStatus status = null;
//
//        try {
//            status = trace.begin("LogTraceService.orderItem()");
//
//            logTraceRepository.save(itemId);
//
//            trace.end(status);
//        } catch (Exception e) {
//            trace.exception(status, e);
//
//            throw e;
//        }
//    }
//}
