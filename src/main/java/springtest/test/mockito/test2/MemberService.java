package springtest.test.mockito.test2;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

    /**
     * 회원 유효성을 검사합니다.
     * 유효하지 않은 회원이면 IllegalArgumentException을 던집니다.
     * (void 메서드 → doThrow 연습용)
     */
    void validate(Long memberId);
}
