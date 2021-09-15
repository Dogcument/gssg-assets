package com.gssg.gssgbe.domain.reply.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.reply.entity.Reply;
import com.gssg.gssgbe.domain.reply.repository.ReplyLikeRepository;
import com.gssg.gssgbe.domain.reply.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class DeleteReplyLikeService {

	private final ReplyLikeRepository replyLikeRepository;
	private final ReplyRepository replyRepository;

	public void delete(final long replyId, final Member loginMember) {
		final Reply reply = replyRepository.findById(replyId)
			.orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

		replyLikeRepository.findByReplyAndMember(reply, loginMember)
			.ifPresent(replyLikeRepository::delete);
	}
}
