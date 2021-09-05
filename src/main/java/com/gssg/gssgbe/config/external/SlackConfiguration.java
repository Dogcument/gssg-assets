package com.gssg.gssgbe.config.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackConfiguration {

	@Value("${application.slack.bot-token}")
	private String botToken;

	@Bean
	public String slackBotToken() {
		return botToken;
	}
}
