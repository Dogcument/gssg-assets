package com.gssg.gssgbe.initdata;

import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import com.gssg.gssgbe.domain.subject.entity.Subject;
import com.gssg.gssgbe.domain.subject.entity.SubjectOfDate;
import com.gssg.gssgbe.domain.subject.repository.SubjectOfDateRepository;
import com.gssg.gssgbe.domain.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

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
        createSubject();
        createSubjectOfDate();
    }

    private void createMember() {
        final List<Member> members = List.of(new Member("gunny@naver.com", "1q2w3e4r!", "gunny", "Hi"),
                new Member("jiwung1994@gmail.com", "1q2w3e4r!", "서당개", "3년차 입니다."),
                new Member("kryunkoo@naver.com", "1q2w3e4r!", "개발자국", "개발자 국"),
                new Member("choi8608@gmail.com", "1q2w3e4r!", "hyune", "dandan"));

        memberRepository.saveAll(members);
    }

    private void createSubject() {
        final List<Subject> subjects = List.of(
                new Subject("가을", "날씨 3"),
                new Subject("글", "날씨 3"),
                new Subject("봄", "날씨 3"),
                new Subject("다이어트", "날씨 3"),
                new Subject("하늘", "날씨 3"),
                new Subject("월요일", "날씨 3"),
                new Subject("아침", "날씨 3"),
                new Subject("퇴근", "날씨 3"),
                new Subject("돼지", "날씨 3"),
                new Subject("거울", "날씨 3"),
                new Subject("컴퓨터", "날씨 3"),
                new Subject("서울", "날씨 3"),
                new Subject("오징어", "날씨 3"),
                new Subject("미세먼지", "날씨 3"),
                new Subject("앵무새", "날씨 3"),
                new Subject("삼겹살", "날씨 3"),
                new Subject("집", "날씨 3"),
                new Subject("비타민", "날씨 3"),
                new Subject("국밥", "날씨 3"),
                new Subject("온기", "날씨 3"),
                new Subject("물", "날씨 3"),
                new Subject("얼굴", "날씨 3"),
                new Subject("핸드폰", "날씨 3"),
                new Subject("치킨", "날씨 3"),
                new Subject("주식", "날씨 3"));

        subjectRepository.saveAll(subjects);
    }

    private void createSubjectOfDate() {
        LocalDate localDate = LocalDate.now().minusDays(10);

        for (final Subject subject : subjectRepository.findAll()) {
            subjectOfDateRepository.save(new SubjectOfDate(subject, localDate));
            localDate = localDate.plusDays(1);
        }
    }
}
