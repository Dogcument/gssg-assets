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

    private String email;

    @Convert(converter = PasswordEncryptConverter.class)
    @Column(name = "password")
    private String password;

    private String nickName;

    @Enumerated(EnumType.STRING)
    @Column(name = "profile_dog")
    private ProfileDogType profileDog;

    private String introduce;

    private String role;

    private Boolean deleted;

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
        this.nickName = "";
        this.profileDog = ProfileDogType.getDefault();
        this.introduce = "";
        this.role = Role.MEMBER.getCode();
        this.deleted = false;
    }

    public Member(String email, String password, String nickName, String introduce) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.profileDog = ProfileDogType.getDefault();
        this.introduce = introduce;
        this.role = Role.MEMBER.getCode();
        this.deleted = false;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateNickname(String nickName) {
        if (nickName != null) {
            this.nickName = nickName;
        }
    }

    public void updateProfileDog(ProfileDogType profileDog) {
        if (profileDog != null) {
            this.profileDog = profileDog;
        }
    }

    public void updateIntroduce(String introduce) {
        if (introduce != null) {
            this.introduce = introduce;
        }
    }
}
