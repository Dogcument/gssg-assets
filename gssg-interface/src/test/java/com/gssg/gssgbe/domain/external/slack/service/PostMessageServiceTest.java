package com.gssg.gssgbe.domain.external.slack.service;

import com.gssg.gssgbe.domain.external.slack.dto.PostMessageDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

/*
    botToken 을 public repository 에 올리지 못하는 이슈로 Disabled
    필요시 yml 을 수정하여 사용
 */
@Disabled
@DisplayName("Slack 웹훅")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PostMessageServiceTest {

    @Autowired
    private PostMessageService postMessageService;

    @DisplayName("[성공] 전송")
    @Test
    void send() {
        // given
        final String webhookTestUrl = "C029TAGE4AE";
        final PostMessageDto postMessageDto = PostMessageDto.of(webhookTestUrl, "Test Message");

        // when
        final String block = postMessageService
                .sendChatPostMessage(postMessageDto)
                .block();

        // then
        assertThat(block).startsWith("{\"ok\":true");
    }
}

