package com.gssg.gssgbe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.gssg.gssgbe.config.logging.MDCTaskDecorator;

@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {

	@Bean
	public TaskExecutor taskExecutor() {
		final ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

		taskExecutor.setTaskDecorator(new MDCTaskDecorator());
		taskExecutor.setCorePoolSize(20);
		taskExecutor.setMaxPoolSize(50);
		taskExecutor.setQueueCapacity(100);

		return taskExecutor;
	}
}
