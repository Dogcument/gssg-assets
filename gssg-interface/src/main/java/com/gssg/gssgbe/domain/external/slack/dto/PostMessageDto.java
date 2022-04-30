package com.gssg.gssgbe.domain.external.slack.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostMessageDto {

    private final String channel;
    private final String text;

    public static PostMessageDto of(final String channel, final String text) {
        return PostMessageDto.builder()
                .channel(channel)
                .text(text)
                .build();
    }
}
