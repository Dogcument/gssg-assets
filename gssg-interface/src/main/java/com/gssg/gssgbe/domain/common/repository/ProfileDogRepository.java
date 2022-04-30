package com.gssg.gssgbe.domain.common.repository;

import com.gssg.gssgbe.domain.common.entity.ProfileDog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDogRepository extends JpaRepository<ProfileDog, Long> {

}
