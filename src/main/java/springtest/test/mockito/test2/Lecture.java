package springtest.test.mockito.test2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int limitCount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member owner;

    public Lecture(String title, int limitCount) {
        this.title = title;
        this.limitCount = limitCount;
    }
}
