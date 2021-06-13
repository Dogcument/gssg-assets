package com.gssg.gssgbe.domain.member.entity;

import com.gssg.gssgbe.common.convert.EncryptConverter;
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
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "member_user")
@Entity
public class Member extends BaseDateTime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_user_id")
  private Long id;

  @Column(name = "email")
  private String email;

  @Convert(converter = EncryptConverter.class)
  @Column(name = "password")
  private String password;

  @Column(name = "nick_name")
  private String nickName;

  @Column(name = "deleted")
  private Boolean deleted;

  public Member(String email, String password, String nickName) {
    this.email = email;
    this.password = password;
    this.nickName = nickName;
    this.deleted = false;
  }

  public boolean validPassword(String password) {
    return this.password.equals(password);
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getNickName() {
    return nickName;
  }
}
