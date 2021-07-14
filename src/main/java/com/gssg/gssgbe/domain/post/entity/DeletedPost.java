package com.gssg.gssgbe.domain.post.entity;

import com.gssg.gssgbe.common.entity.BaseDeletedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
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
    private Long id;

    @Column(name = "member_user_id")
    private Long memberId;

    @Column(name = "content")
    private String content;
}
