package com.gssg.gssgbe.util;

import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import com.gssg.gssgbe.domain.post.entity.Post;
import com.gssg.gssgbe.domain.post.repository.PostRepository;
import com.gssg.gssgbe.domain.subject.entity.Subject;
import com.gssg.gssgbe.domain.subject.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestPostInit {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private PostRepository postRepository;

    @Bean
    public void testPostInit() {
        final Subject subject = subjectRepository.findById(1L).get();
        memberRepository.findAll()
                .forEach(member ->
                        postRepository.save(Post.of(member, subject, member.getNickname() + " contents")));
    }
}
