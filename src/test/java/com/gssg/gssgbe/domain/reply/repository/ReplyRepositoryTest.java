package com.gssg.gssgbe.domain.reply.repository;

import static org.junit.jupiter.api.DynamicTest.*;
import static org.springframework.boot.test.context.SpringBootTest.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import com.gssg.gssgbe.domain.reply.entity.Reply;
import com.gssg.gssgbe.domain.reply.entity.ReplyLike;
import com.gssg.gssgbe.util.TestMemberInit;
import com.gssg.gssgbe.util.TestPostInit;
import com.gssg.gssgbe.util.TestReplyInit;
import com.gssg.gssgbe.util.TestSubjectInit;

@DisplayName("[repo] 댓글 좋아요")
@Transactional
@Import({TestMemberInit.class, TestSubjectInit.class, TestPostInit.class, TestReplyInit.class})
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ReplyRepositoryTest {

	@Autowired private MemberRepository memberRepository;
	@Autowired private ReplyRepository replyRepository;
	@Autowired private ReplyLikeRepository replyLikeRepository;

	@TestFactory
	Stream<DynamicNode> find() {
		// 댓글 좋아요 개수 순서를 섞어서 생성
		final List<Member> members = memberRepository.findAll();
		createReplyLike(replyRepository.getById(1L), members.subList(0, 2));
		createReplyLike(replyRepository.getById(2L), members);
		createReplyLike(replyRepository.getById(3L), members.subList(0, 3));
		createReplyLike(replyRepository.getById(4L), members.subList(0, 1));

		return Stream.of(
			dynamicTest("조회", () -> {
				// given

				// when
				System.out.println("### " + replyRepository.findById(1L).get().getReplyLikes().size());
				System.out.println("### " + replyRepository.findById(2L).get().getReplyLikes().size());
				System.out.println("### " + replyRepository.findById(3L).get().getReplyLikes().size());
				System.out.println("### " + replyRepository.findById(4L).get().getReplyLikes().size());

				// then
			})
		);
	}

	private void createReplyLike(final Reply reply, final List<Member> members) {
		members.forEach(member -> replyLikeRepository.save(new ReplyLike(reply, member)));
	}
}
