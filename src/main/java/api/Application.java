package api;

import java.util.Map;
import java.util.Optional;

import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

import rx.plugins.RxJavaHooks;

@SpringCloudApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    static {
        RxJavaHooks.setOnScheduleAction(action -> {
            Optional<Map<String, String>> context = Optional.ofNullable(MDC.getCopyOfContextMap());
            return () -> {
                Optional<Map<String, String>> originalMdc = Optional.ofNullable(MDC.getCopyOfContextMap()) ;
                context.ifPresent(MDC::setContextMap);
                try {
                    action.call();
                } finally {
                    originalMdc.ifPresent(MDC::setContextMap);
                }
            };
        });
    }
}
