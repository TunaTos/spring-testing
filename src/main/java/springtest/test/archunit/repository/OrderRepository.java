package springtest.test.archunit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springtest.test.archunit.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
