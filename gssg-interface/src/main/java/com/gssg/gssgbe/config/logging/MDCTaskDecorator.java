package com.gssg.gssgbe.config.logging;

import java.util.Map;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

public class MDCTaskDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(final Runnable runnable) {
        final Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();

        return () -> {
            if (copyOfContextMap != null) {
                MDC.setContextMap(copyOfContextMap);
            }

            runnable.run();
        };
    }
}
