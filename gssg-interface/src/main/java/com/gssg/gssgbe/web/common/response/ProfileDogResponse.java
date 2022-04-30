package com.gssg.gssgbe.web.common.response;

import com.gssg.gssgbe.domain.common.dto.ProfileDogDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileDogResponse {

    @Schema(description = "영어면")
    private String englishName;

    @Schema(description = "한글명")
    private String koreanName;

    @Schema(description = "강조")
    private Boolean highlight;

    public ProfileDogResponse(final ProfileDogDto dto) {
        this.englishName = dto.getEnglishName();
        this.koreanName = dto.getKoreanName();
        this.highlight = dto.getHighlight();
    }
}
