package com.gssg.gssgbe.data;

import com.gssg.gssgbe.user.entity.User;
import java.util.stream.Stream;

public class TestData {

  public static Stream<User> VALID_USER() {
    return Stream.of(
        new User("choi@gmail.com", "1q2w3e4r*", "dan")
    );
  }

  public static Stream<User> NOT_VALID_USER() {
    String maxEmailId = "choichoichoichoichoichoichoichoichoichoichoichoichoichoichoichoi";
    String maxMailServerName = "gmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmail"
        + "gmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmailgmail.com";

    return Stream.of(
        new User("choigmail.com", "1q2w3e4r*", "dan"),
        new User(maxEmailId + "@" + maxMailServerName, "1q2w3e4r*", "dan"),
        new User("choi@gmail.com", "1111", "dan"),
        new User("choi@gmail.com", "1q2w3e4r", "dan"),
        new User("choi@gmail.com", "1q2w*", "dan")
    );
  }
}
