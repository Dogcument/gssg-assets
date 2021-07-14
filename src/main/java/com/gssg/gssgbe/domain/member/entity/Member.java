package com.gssg.gssgbe.domain.member.entity;

import com.gssg.gssgbe.common.converter.PasswordEncryptConverter;
import com.gssg.gssgbe.common.entity.BaseDateTime;
import com.gssg.gssgbe.common.token.Role;
import com.gssg.gssgbe.common.type.ProfileDogType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Column(name = "email")
    private String email;

    @Convert(converter = PasswordEncryptConverter.class)
    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickName;

    @Enumerated(EnumType.STRING)
    @Column(name = "profile_dog")
    private ProfileDogType profileDog;

    @Column(name = "role")
    private String role;

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
        this.nickName = "";
        this.profileDog = ProfileDogType.getDefault();
        this.role = Role.MEMBER.getCode();
    }
}
