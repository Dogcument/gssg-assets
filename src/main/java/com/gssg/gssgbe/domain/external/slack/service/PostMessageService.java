package com.gssg.gssgbe.domain.external.slack.service;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.gssg.gssgbe.domain.external.slack.dto.PostMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PostMessageService {

    private final String slackBotToken;
    private final WebClient webClient;

    private static final String chatPostMessage = "https://slack.com/api/chat.postMessage";

    public Mono<String> sendChatPostMessage(PostMessageDto postMessageDto) {
        return webClient.post()
            .uri(chatPostMessage)
            .headers(headers -> headers.add(AUTHORIZATION, "Bearer " + slackBotToken))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(postMessageDto)
            .retrieve()
            .bodyToMono(String.class);
    }
}
