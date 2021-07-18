package com.gssg.gssgbe.domain.member.entity;

import com.gssg.gssgbe.common.entity.BaseDeletedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "deleted_member_user")
@Entity
public class DeletedMember extends BaseDeletedDateTime {

    @Id
    @Column(name = "deleted_member_user_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "reason")
    private String reason;
}
