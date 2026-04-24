package springtest.test.archunit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springtest.test.archunit.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
