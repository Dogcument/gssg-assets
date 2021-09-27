package com.gssg.gssgbe.domain.reply.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.reply.dto.response.ReplyDto;
import com.gssg.gssgbe.domain.reply.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FindReplyService {

	private final ReplyRepository replyRepository;

	public Slice<ReplyDto> findAll(final Pageable pageable) {
		return replyRepository.findAllSlice(pageable)
			.map(ReplyDto::new);
	}

	public Slice<ReplyDto> findAllByPostId(final long postId, final Pageable pageable) {
		return replyRepository.findAllByPostId(postId, pageable)
			.map(ReplyDto::new);
	}

	public Slice<ReplyDto> findByMember(final Member loginMember, final Pageable pageable) {
		return replyRepository.findAllByMember(loginMember, pageable)
			.map(ReplyDto::new);
	}
}
