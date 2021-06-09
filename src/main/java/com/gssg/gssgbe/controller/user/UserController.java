package com.gssg.gssgbe.controller.user;

import com.gssg.gssgbe.controller.user.request.UserCreateRequest;
import com.gssg.gssgbe.controller.user.request.UserLoginRequest;
import com.gssg.gssgbe.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

  private final UserService userService;

  @Operation(summary = "가입")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/api/v1/users")
  public Long create(@RequestBody @Valid UserCreateRequest request) {
    return userService.create(request);
  }

  @Operation(summary = "로그인")
  @PostMapping("/api/v1/users/login")
  public void login(@RequestBody @Valid UserLoginRequest request) {
    userService.login(request.getLoginId(), request.getPassword());
  }
}
