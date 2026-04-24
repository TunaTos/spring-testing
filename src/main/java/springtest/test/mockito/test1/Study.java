package springtest.test.mockito.test1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int limitCount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member owner;

    public Study(String title, int limitCount) {
        this.title = title;
        this.limitCount = limitCount;
    }
}
