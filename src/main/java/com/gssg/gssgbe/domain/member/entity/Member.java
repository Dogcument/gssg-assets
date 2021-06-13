package com.gssg.gssgbe.domain.member.entity;

import com.gssg.gssgbe.common.converter.PasswordEncryptConverter;
import com.gssg.gssgbe.common.entity.BaseDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "member_user")
@Entity
public class Member extends BaseDateTime {

  @Getter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_user_id")
  private Long id;

  @Getter
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Getter
  @Convert(converter = PasswordEncryptConverter.class)
  @Column(name = "password", nullable = false)
  private String password;

  @Getter
  @Column(name = "nick_name", nullable = false, unique = true)
  private String nickName;

  @Column(name = "deleted", nullable = false)
  private Boolean deleted;

  public Member(String email, String password, String nickName) {
    this.email = email;
    this.password = password;
    this.nickName = nickName;
    this.deleted = false;
  }
}
