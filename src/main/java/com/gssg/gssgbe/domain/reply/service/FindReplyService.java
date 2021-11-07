package com.gssg.gssgbe.domain.reply.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gssg.gssgbe.common.clazz.NoOffsetPageRequest;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.reply.dto.response.ReplyDto;
import com.gssg.gssgbe.domain.reply.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FindReplyService {

	private final ReplyRepository replyRepository;

	public List<ReplyDto> findAllByPostId(final Member loginMember, final long postId,
		final NoOffsetPageRequest pageRequest) {
		return replyRepository.findAllByPostId(postId, pageRequest).stream()
			.map(reply -> ReplyDto.of(reply, reply.isLike(loginMember)))
			.collect(Collectors.toList());
	}
}
