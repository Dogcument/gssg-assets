package com.gssg.gssgbe.web.post;

import com.gssg.gssgbe.common.annotation.LoginMember;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.post.service.CreatePostService;
import com.gssg.gssgbe.web.post.request.CreatePostRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "글")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final CreatePostService createPostService;

    @Operation(summary = "글 작성")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/posts")
    public Long create(
        @LoginMember Member loginMember,
        @RequestBody @Valid CreatePostRequest request) {
        return createPostService.create(request.toDto(loginMember));
    }
}
