package com.gssg.gssgbe.controller.member;

import com.gssg.gssgbe.domain.member.dto.request.CreateMemberRequest;
import com.gssg.gssgbe.domain.member.dto.request.LoginMemberRequest;
import com.gssg.gssgbe.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원")
@RequiredArgsConstructor
@RestController
public class MemberController {

  private final MemberService memberService;

  @Operation(summary = "가입")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/api/v1/members")
  public Long create(@RequestBody @Valid CreateMemberRequest request) {
    return memberService.create(request);
  }

  @Operation(summary = "로그인")
  @PostMapping("/api/v1/members/login")
  public void login(@RequestBody @Valid LoginMemberRequest request) {
    memberService.login(request.getLoginId(), request.getPassword());
  }
}
