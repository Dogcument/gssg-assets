package com.gssg.gssgbe.domain.subject.repository;

import com.gssg.gssgbe.domain.subject.entity.SubjectOfDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectOfDateRepository extends JpaRepository<SubjectOfDate, Long> {

    Optional<SubjectOfDate> findByDate(LocalDate date);

    List<SubjectOfDate> findAllByDateBetween(final LocalDate from, final LocalDate to);
}
