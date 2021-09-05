package com.gssg.gssgbe.data;

import java.util.stream.Stream;

import com.gssg.gssgbe.domain.member.entity.Member;

public class TestData {

	public static Stream<Member> VALID_MEMBER() {
		return Stream.of(
			new Member("gunny@naver.com", "1q2w3e4r!", "gunny", "Hi"),
			new Member("jiwung1994@gmail.com", "1q2w3e4r!", "서당개", "3년차 입니다."),
			new Member("kryunkoo@naver.com", "1q2w3e4r!", "개발자국", "개발자 국"),
			new Member("choi8608@gmail.com", "1q2w3e4r!", "hyune", "dandan")
		);
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
