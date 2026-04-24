package springtest.test.archunit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springtest.test.archunit.domain.Member;
import springtest.test.archunit.domain.Order;
import springtest.test.archunit.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberService memberService;

    public Order createOrder(Long memberId, String itemName, int quantity, int totalPrice) {
        Member member = memberService.findById(memberId);
        Order order = new Order(itemName, quantity, totalPrice, member);
        return orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
