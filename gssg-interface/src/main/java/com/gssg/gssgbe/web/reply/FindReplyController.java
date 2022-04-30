package com.gssg.gssgbe.web.reply;

import com.gssg.gssgbe.common.annotation.LoginMember;
import com.gssg.gssgbe.common.clazz.NoOffsetPageRequest;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.reply.dto.response.ReplyDto;
import com.gssg.gssgbe.domain.reply.service.FindReplyService;
import com.gssg.gssgbe.web.reply.response.FindAllReplyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nullable;
import javax.validation.constraints.Positive;
import java.util.List;

import static com.gssg.gssgbe.domain.reply.repository.ReplyRepositoryImpl.SortType;

@Tag(name = "댓글")
@Validated
@RequiredArgsConstructor
@RestController
public class FindReplyController {

    private final FindReplyService findReplyService;

    @Operation(summary = "글의 댓글 조회", security = @SecurityRequirement(name = "bearerAuth"), description = "좋아요 순서는 paging 불가")
    @GetMapping("/api/v1/posts/{postId}/replies")
    public FindAllReplyResponse findAll(
            @Parameter(hidden = true) @LoginMember final Member loginMember,
            @PathVariable final Long postId,
            @RequestParam @Nullable @Positive final Long currentReplyId,
            @RequestParam(defaultValue = "10") @Positive final Integer size,
            @RequestParam(defaultValue = "ID") final SortType sortType) {
        final NoOffsetPageRequest pageRequest = NoOffsetPageRequest.of(currentReplyId, size, Sort.by(sortType.name()));
        final List<ReplyDto> replyDtos = findReplyService.findAllByPostId(loginMember, postId, pageRequest);

        return FindAllReplyResponse.of(replyDtos);
    }
}
