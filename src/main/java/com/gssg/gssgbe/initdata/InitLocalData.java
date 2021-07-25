package com.gssg.gssgbe.initdata;

import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import com.gssg.gssgbe.domain.subject.entity.Subject;
import com.gssg.gssgbe.domain.subject.entity.SubjectOfDate;
import com.gssg.gssgbe.domain.subject.repository.SubjectOfDateRepository;
import com.gssg.gssgbe.domain.subject.repository.SubjectRepository;
import java.time.LocalDate;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Profile("local")
@Component
public class InitLocalData {

    private final MemberRepository memberRepository;
    private final SubjectRepository subjectRepository;
    private final SubjectOfDateRepository subjectOfDateRepository;

    @PostConstruct
    public void postConstruct() {
        createMember();

        Subject subject = new Subject("가을", "날씨 3");
        subjectRepository.save(subject);

        SubjectOfDate subjectOfDate = new SubjectOfDate(subject, LocalDate.now());
        subjectOfDateRepository.save(subjectOfDate);
    }

    private void createMember() {
        List<Member> members = List.of(new Member("gunny@naver.com", "1q2w3e4r!", "gunny", "Hi"),
            new Member("jiwung1994@gmail.com", "1q2w3e4r!", "서당개", "3년차 입니다."),
            new Member("kryunkoo@naver.com", "1q2w3e4r!", "개발자국", "개발자 국"),
            new Member("choi8608@gmail.com", "1q2w3e4r!", "hyune", "dandan"));

        memberRepository.saveAll(members);
    }
}
