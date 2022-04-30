package com.gssg.gssgbe.domain.common.dto;

import com.gssg.gssgbe.domain.common.entity.ProfileDog;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileDogDto {

    private String englishName;
    private String koreanName;
    private Boolean highlight;

    public static ProfileDogDto of(final ProfileDog profileDog) {
        return ProfileDogDto.builder()
                .englishName(profileDog.getEnglishName())
                .koreanName(profileDog.getKoreanName())
                .highlight(profileDog.getHighlight())
                .build();
    }
}
