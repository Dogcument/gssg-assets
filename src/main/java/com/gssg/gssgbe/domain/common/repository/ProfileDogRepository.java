package com.gssg.gssgbe.domain.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gssg.gssgbe.domain.common.entity.ProfileDog;

@Repository
public interface ProfileDogRepository extends JpaRepository<ProfileDog, Long> {

}
