package com.gssg.gssgbe.domain.post.entity;

import com.gssg.gssgbe.common.entity.BaseDeletedDateTime;
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

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "deleted_post")
@Entity
public class DeletedPost extends BaseDeletedDateTime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "deleted_post_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_user_id", nullable = false)
  private Member writer;

  @Column(name = "content", nullable = false)
  private String content;

  public DeletedPost(Member writer, String content) {
    this.writer = writer;
    this.content = content;
  }
}
