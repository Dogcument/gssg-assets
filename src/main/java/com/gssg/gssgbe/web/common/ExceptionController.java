package com.gssg.gssgbe.web.common;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.web.common.response.ErrorCodeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "공통")
@RestController
public class ExceptionController {

  @Operation(summary = "에러 코드")
  @GetMapping("/api/v1/common/errorCode")
  public List<ErrorCodeResponse> findAll() {
    return Arrays.stream(ErrorCode.values())
        .map(ErrorCodeResponse::new)
        .collect(Collectors.toList());
  }
}
