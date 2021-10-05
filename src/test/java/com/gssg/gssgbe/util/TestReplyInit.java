package com.gssg.gssgbe.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import com.gssg.gssgbe.domain.post.entity.Post;
import com.gssg.gssgbe.domain.post.repository.PostRepository;
import com.gssg.gssgbe.domain.reply.entity.Reply;
import com.gssg.gssgbe.domain.reply.repository.ReplyRepository;

@TestConfiguration
public class TestReplyInit {

	@Autowired private MemberRepository memberRepository;
	@Autowired private PostRepository postRepository;
	@Autowired private ReplyRepository replyRepository;

	@Bean
	public void testReplyInit() {
		final Post post = postRepository.findById(1L).get();
		memberRepository.findAll().forEach(member ->
			replyRepository.save(new Reply(member, post, member.getNickname() + " reply")));
	}
}
