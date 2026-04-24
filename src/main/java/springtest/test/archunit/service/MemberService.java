package springtest.test.archunit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springtest.test.archunit.domain.Member;
import springtest.test.archunit.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. id=" + id));
    }
}
