package com.gssg.gssgbe.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import com.gssg.gssgbe.domain.post.entity.Post;
import com.gssg.gssgbe.domain.post.repository.PostRepository;
import com.gssg.gssgbe.domain.subject.entity.Subject;
import com.gssg.gssgbe.domain.subject.repository.SubjectRepository;

@TestConfiguration
public class TestReplyInit {

	@Autowired private MemberRepository memberRepository;
	@Autowired private SubjectRepository subjectRepository;
	@Autowired private PostRepository postRepository;

	@Bean
	public void testPostDataInit() {
		final Subject subject = subjectRepository.findById(1L).get();
		memberRepository.findAll()
			.forEach(member ->
				postRepository.save(new Post(member, subject, member.getNickname() + " contents")));
	}
}
