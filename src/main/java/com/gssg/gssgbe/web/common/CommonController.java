package com.gssg.gssgbe.web.common;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gssg.gssgbe.common.type.ProfileDogType;
import com.gssg.gssgbe.web.common.response.ProfileDogResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "공통")
@RestController
public class CommonController {

	@Operation(summary = "강아지")
	@GetMapping("/api/v1/common/profileDogs")
	public List<ProfileDogResponse> findAllProfileDogs() {
		return Arrays.stream(ProfileDogType.values())
			.map(ProfileDogResponse::new)
			.collect(Collectors.toList());
	}
}
