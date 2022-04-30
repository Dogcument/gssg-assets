package com.gssg.gssgbe.domain.subject.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "subject_of_date")
@Entity
public class SubjectOfDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private LocalDate date;

    public SubjectOfDate(final Subject subject, final LocalDate date) {
        this.subject = subject;
        this.date = date;
    }
}
