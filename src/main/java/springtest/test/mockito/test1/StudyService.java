package springtest.test.mockito.test1;

import java.util.Optional;

public class StudyService {

    private final MemberService memberService;
    private final StudyRepository studyRepository;

    public StudyService(MemberService memberService, StudyRepository studyRepository) {
        this.memberService = memberService;
        this.studyRepository = studyRepository;
    }

    /**
     * 스터디를 생성합니다.
     * memberId에 해당하는 회원이 존재하면 스터디의 owner로 지정합니다.
     */
    public Study createStudy(Long memberId, Study study) {
        Optional<Member> member = memberService.findById(memberId);
        member.ifPresent(study::setOwner);
        return studyRepository.save(study);
    }
}
