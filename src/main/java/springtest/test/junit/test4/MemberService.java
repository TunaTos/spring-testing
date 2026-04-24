package springtest.test.junit.test4;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberService {

    private final List<Member> members = new ArrayList<>();
    private long nextId = 1L;

    /**
     * 회원을 등록합니다.
     * 이름 또는 이메일이 비어 있으면 예외를 던집니다.
     */
    public Member register(String name, String email) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 필수입니다.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("이메일은 필수입니다.");
        }
        Member member = new Member(nextId++, name, email);
        members.add(member);
        return member;
    }

    /**
     * ID로 회원을 조회합니다.
     */
    public Optional<Member> findById(Long id) {
        return members.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    /**
     * 회원을 비활성화합니다.
     * 존재하지 않는 ID면 예외를 던집니다.
     */
    public void deactivate(Long id) {
        Member member = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        member.setActive(false);
    }

    /**
     * 전체 회원 수를 반환합니다.
     */
    public int count() {
        return members.size();
    }

    /**
     * 활성 회원 수를 반환합니다.
     */
    public long countActive() {
        return members.stream().filter(Member::isActive).count();
    }
}
