package com.gssg.gssgbe.data;

import com.gssg.gssgbe.domain.member.entity.Member;

import java.util.stream.Stream;

public class TestData {

    public static Stream<Member> VALID_MEMBER() {
        return Stream.of(
                Member.builder()
                        .id(1L)
                        .email("gunny@naver.com")
                        .password("1q2w3e4r!")
                        .nickname("gunny")
                        .introduce("Hi")
                        .build(),
                Member.builder()
                        .id(2L)
                        .email("jiwung1994@gmail.com")
                        .password("1q2w3e4r!")
                        .nickname("서당개")
                        .introduce("3년차 입니다.")
                        .build(),
                Member.builder()
                        .id(3L)
                        .email("kryunkoo@naver.com")
                        .password("1q2w3e4r!")
                        .nickname("개발자국")
                        .introduce("개발자 국")
                        .build(),
                Member.builder()
                        .id(4L)
                        .email("choi8608@gmail.com")
                        .password("1q2w3e4r!")
                        .nickname("hyune")
                        .introduce("dandan")
                        .build());
    }

    public static Stream<Member> NOT_VALID_MEMBER() {
        final String maxEmailId = "choichoichoichoichoichoichoichoichoichoichoichoichoichoichoichoi";
        final String maxMailServerName =
                "gmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmail"
                        + "gmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmail.com";

        return Stream.of(
                new Member("choigmail.com", "1q2w3e4r*"),
                new Member(maxEmailId + "@" + maxMailServerName, "1q2w3e4r*"),
                new Member("choi@gmail.com", "1111"),
                new Member("choi@gmail.com", "1q2w3e4r"),
                new Member("choi@gmail.com", "1q2w*")
        );
    }
}
