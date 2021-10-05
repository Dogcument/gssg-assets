package com.gssg.gssgbe.domain.member.entity;

import static lombok.AccessLevel.*;

import java.util.Objects;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.gssg.gssgbe.common.converter.PasswordEncryptConverter;
import com.gssg.gssgbe.common.entity.BaseDateTime;
import com.gssg.gssgbe.common.token.Role;
import com.gssg.gssgbe.common.type.ProfileDogType;
import com.gssg.gssgbe.domain.member.dto.request.CreateMemberDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@DynamicUpdate
@Table(name = "member_user")
@Entity
public class Member extends BaseDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	@Convert(converter = PasswordEncryptConverter.class)
	private String password;

	private String nickname;

	@Enumerated(EnumType.STRING)
	private ProfileDogType profileDog;

	private String introduce;

	private String role;

	private Boolean deleted;

	public Member(final String email, final String password) {
		this.email = email;
		this.password = password;
		this.nickname = "";
		this.profileDog = ProfileDogType.getDefault();
		this.introduce = "";
		this.role = Role.MEMBER.getCode();
		this.deleted = false;
	}

	public Member(final String email, final String password, final String nickname, final String introduce) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.profileDog = ProfileDogType.getDefault();
		this.introduce = introduce;
		this.role = Role.MEMBER.getCode();
		this.deleted = false;
	}

	public Member(final CreateMemberDto dto) {
		this.email = dto.getEmail();
		this.password = dto.getPassword();
		this.nickname = dto.getNickname();
		this.profileDog = dto.getProfileDogType();
		this.introduce = dto.getIntroduce();
		this.role = Role.MEMBER.getCode();
		this.deleted = false;
	}

	public void updatePassword(final String password) {
		this.password = password;
	}

	public void updateNickname(final String nickname) {
		if (nickname != null) {
			this.nickname = nickname;
		}
	}

	public void updateProfileDog(final ProfileDogType profileDog) {
		if (profileDog != null) {
			this.profileDog = profileDog;
		}
	}

	public void updateIntroduce(final String introduce) {
		if (introduce != null) {
			this.introduce = introduce;
		}
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Member))
			return false;
		final Member member = (Member)o;
		return Objects.equals(getEmail(), member.getEmail());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getEmail());
	}
}
