package com.gssg.gssgbe.domain.post.entity;

import com.gssg.gssgbe.common.entity.BaseDateTime;
import com.gssg.gssgbe.domain.member.entity.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "post")
@Entity
public class Post extends BaseDateTime {

  @Getter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "post_id")
  private Long id;

  @Getter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_user_id", nullable = false)
  private Member writer;

  @Getter
  @Column(name = "content", nullable = false)
  private String content;

  @Column(name = "deleted", nullable = false)
  private Boolean deleted;

  public Post(Member writer, String content) {
    this.writer = writer;
    this.content = content;
    this.deleted = false;
  }
}
