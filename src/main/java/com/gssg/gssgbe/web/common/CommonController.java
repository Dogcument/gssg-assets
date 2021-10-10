package com.gssg.gssgbe.web.common;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gssg.gssgbe.domain.common.service.FindProfileDogService;
import com.gssg.gssgbe.web.common.response.ProfileDogResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
	public void refresh() {
		log.info("### profileDogs refresh");
	}
}
