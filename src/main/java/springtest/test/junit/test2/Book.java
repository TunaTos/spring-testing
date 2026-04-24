package springtest.test.junit.test2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private String title;
    private String author;
    private BookStatus status;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.status = BookStatus.AVAILABLE;
    }
}
