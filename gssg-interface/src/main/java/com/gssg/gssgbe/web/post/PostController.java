package com.gssg.gssgbe.web.post;

import com.gssg.gssgbe.common.annotation.LoginMember;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.post.service.CreatePostLikeService;
import com.gssg.gssgbe.domain.post.service.CreatePostService;
import com.gssg.gssgbe.domain.post.service.DeletePostLikeService;
import com.gssg.gssgbe.domain.post.service.FindPostLikeService;
import com.gssg.gssgbe.web.post.request.CreatePostRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "글")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final CreatePostService createPostService;
    private final FindPostLikeService findPostLikeService;
    private final CreatePostLikeService createPostLikeService;
    private final DeletePostLikeService deletePostLikeService;

    @Operation(summary = "글 작성", security = @SecurityRequirement(name = "bearerAuth"))
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/posts")
    public Long create(
            @Parameter(hidden = true) @LoginMember final Member loginMember,
            @RequestBody @Valid final CreatePostRequest request) {
        return createPostService.create(request.toDto(loginMember));
    }

    @Operation(summary = "글 좋아요 토글", security = @SecurityRequirement(name = "bearerAuth"))
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/posts/{postId}/like")
    public Boolean like(
            @Parameter(hidden = true) @LoginMember final Member loginMember,
            @PathVariable final Long postId) {
        if (findPostLikeService.exist(postId, loginMember)) {
            deletePostLikeService.delete(postId, loginMember);
            return false;
        }

        createPostLikeService.create(postId, loginMember);
        return true;
    }
}
