package com.gssg.gssgbe.domain.subject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gssg.gssgbe.domain.subject.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

	Optional<Subject> findByName(String name);
}
