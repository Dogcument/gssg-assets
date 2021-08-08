package com.gssg.gssgbe.domain.external.slack.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostMessageDto {

    private final String channel;
    private final String text;

    public static PostMessageDto of(String channel, String text) {
        return new PostMessageDto(channel, text);
    }
}
