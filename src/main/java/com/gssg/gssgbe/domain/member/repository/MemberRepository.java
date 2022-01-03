package com.gssg.gssgbe.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gssg.gssgbe.domain.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByEmail(String email);
	Optional<Member> findByNickname(String nickname);

	boolean existsByEmail(String email);

	boolean existsByNickname(String nickname);
}
