package com.gssg.gssgbe.web.common;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.domain.common.service.FindProfileDogService;
import com.gssg.gssgbe.web.common.response.ErrorCodeResponse;
import com.gssg.gssgbe.web.common.response.ProfileDogResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "공통")
@Slf4j
@RequiredArgsConstructor
@RestController
public class CommonController {

    private final FindProfileDogService findProfileDogService;

    @Cacheable("profileDog")
    @Operation(summary = "강아지")
    @GetMapping("/api/v1/common/profileDogs")
    public List<ProfileDogResponse> findAllProfileDogs() {
        return findProfileDogService.findAll().stream()
            .map(ProfileDogResponse::new)
            .collect(Collectors.toList());
    }

    @CacheEvict(allEntries = true, value = "profileDog")
    @Scheduled(cron = "0 0 0 * * *")
    @Operation(summary = "강아지 - 캐시 초기화")
    @GetMapping("/api/v1/common/profileDogs/refresh")
    public void refresh() {
        log.info("### profileDogs refresh");
    }

    @Operation(summary = "에러 코드")
    @GetMapping("/api/v1/common/errorCode")
    public List<ErrorCodeResponse> findAll() {
        return Arrays.stream(ErrorCode.values())
            .map(ErrorCodeResponse::new)
            .collect(Collectors.toList());
    }

    @Operation(summary = "헬스 체크 - 서버 기동 시간")
    @GetMapping("/api/v1/common/healthCheck")
    public LocalDateTime healthCheck() {
        return LocalDateTime.now();
    }
}
