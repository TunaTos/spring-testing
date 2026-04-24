package springtest.test.mockito.test2;

import java.util.Optional;

public class LectureService {

    private final MemberService memberService;
    private final LectureRepository lectureRepository;

    public LectureService(MemberService memberService, LectureRepository lectureRepository) {
        this.memberService = memberService;
        this.lectureRepository = lectureRepository;
    }

    /**
     * 강의를 생성합니다.    @ParameterizedTest
     * memberId에 해당하는 회원이 존재하면 owner로 지정합니다.
     * 회원이 없으면 IllegalArgumentException을 던집니다.
     */
    public Lecture createLecture(Long memberId, Lecture lecture) {
        Member member = memberService.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. id=" + memberId));
        lecture.setOwner(member);
        return lectureRepository.save(lecture);
    }

    /**
     * ID로 강의를 조회합니다.
     * 없으면 IllegalStateException을 던집니다.
     */
    public Lecture findLecture(Long lectureId) {
        return lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 강의입니다. id=" + lectureId));
    }

    /**
     * 회원을 강의에 등록합니다.
     * memberService.validate()가 예외를 던지면 그대로 전파됩니다.
     */
    public void enrollMember(Long memberId, Long lectureId) {
        memberService.validate(memberId);
        Lecture lecture = findLecture(lectureId);
        System.out.println(lecture.getTitle() + " 강의에 회원 " + memberId + " 등록 완료");
    }
}
