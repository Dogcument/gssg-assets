package com.gssg.gssgbe.user.repository;

import com.gssg.gssgbe.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByLoginId(String loginId);
}
