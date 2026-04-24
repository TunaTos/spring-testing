package springtest.test.archunit.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springtest.test.archunit.domain.Member;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private int quantity;

    private int totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Order(String itemName, int quantity, int totalPrice, Member member) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.member = member;
    }
}
