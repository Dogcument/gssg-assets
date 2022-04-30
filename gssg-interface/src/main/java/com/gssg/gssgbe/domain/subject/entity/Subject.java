package com.gssg.gssgbe.domain.subject.entity;

import com.gssg.gssgbe.common.entity.BaseDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "subject")
@Entity
public class Subject extends BaseDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Subject(final String name, final String description) {
        this.name = name;
        this.description = description;
    }
}
