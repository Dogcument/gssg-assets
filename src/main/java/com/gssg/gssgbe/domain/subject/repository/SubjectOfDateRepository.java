package com.gssg.gssgbe.domain.subject.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gssg.gssgbe.domain.subject.entity.SubjectOfDate;

@Repository
public interface SubjectOfDateRepository extends JpaRepository<SubjectOfDate, Long> {

	Optional<SubjectOfDate> findByDate(LocalDate date);
}
