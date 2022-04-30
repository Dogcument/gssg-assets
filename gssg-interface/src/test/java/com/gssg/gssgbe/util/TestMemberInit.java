package com.gssg.gssgbe.util;

import com.gssg.gssgbe.data.TestData;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestMemberInit {

    @Autowired
    private MemberRepository memberRepository;

    @Bean
    public void testMemberInit() {
        TestData.VALID_MEMBER().forEach(member -> memberRepository.save(member));
    }
}
