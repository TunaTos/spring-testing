package springtest.test.mockito.test1;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);
}
