package com.gssg.gssgbe.data;

import com.gssg.gssgbe.domain.member.entity.Member;
import java.util.stream.Stream;

public class TestData {

    public static Stream<Member> VALID_MEMBER() {
        return Stream.of(
            new Member("choi@gmail.com", "1q2w3e4r*")
        );
    }

    public static Stream<Member> NOT_VALID_MEMBER() {
        String maxEmailId = "choichoichoichoichoichoichoichoichoichoichoichoichoichoichoichoi";
        String maxMailServerName = "gmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmail"
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
