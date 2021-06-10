package com.gssg.gssgbe.user.entity;

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
@Table(name = "user")
@Entity
public class User extends BaseDateTime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @Column(name = "login_id")
  private String loginId;

  @Convert(converter = EncryptConverter.class)
  @Column(name = "password")
  private String password;

  @Column(name = "nick_name")
  private String nickName;

  @Column(name = "deleted")
  private Boolean deleted;

  public User(String loginId, String password, String nickName) {
    this.loginId = loginId;
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

  public String getLoginId() {
    return loginId;
  }

  public String getPassword() {
    return password;
  }

  public String getNickName() {
    return nickName;
  }
}
