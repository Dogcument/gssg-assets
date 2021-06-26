package com.gssg.gssgbe.domain.member.entity;

import com.gssg.gssgbe.common.converter.PasswordEncryptConverter;
import com.gssg.gssgbe.common.entity.BaseDateTime;
import com.gssg.gssgbe.common.token.Role;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_user")
@Entity
public class Member extends BaseDateTime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_user_id")
  private Long id;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Convert(converter = PasswordEncryptConverter.class)
  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "nickname", nullable = false, unique = true)
  private String nickName;

  @Column(name = "role", nullable = false)
  private String role;

  public Member(String email, String password, String nickName) {
    this.email = email;
    this.password = password;
    this.nickName = nickName;
    this.role = Role.MEMBER.getCode();
  }
}
