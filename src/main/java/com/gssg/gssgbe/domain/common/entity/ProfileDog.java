package com.gssg.gssgbe.domain.common.entity;

import static lombok.AccessLevel.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.gssg.gssgbe.common.entity.BaseDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@Entity
public class ProfileDog extends BaseDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String englishName;
	private String koreanName;
	private Boolean highlight;

	public ProfileDog(final String englishName, final String koreanName) {
		new ProfileDog(null, englishName, koreanName, false);
	}

	public ProfileDog of(final String englishName, final String koreanName) {
		return new ProfileDog(englishName, koreanName);
	}
}
