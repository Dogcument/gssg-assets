package com.gssg.gssgbe.controller.auth;

import com.gssg.gssgbe.domain.member.dto.request.LoginMemberRequest;
import com.gssg.gssgbe.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증")
@RequiredArgsConstructor
@RestController
public class LoginController {

  private final MemberService memberService;

  @Operation(summary = "로그인")
  @PostMapping("/api/v1/login")
  public void login(@RequestBody @Valid LoginMemberRequest request) {
    memberService.login(request.getLoginId(), request.getPassword());
  }
}
