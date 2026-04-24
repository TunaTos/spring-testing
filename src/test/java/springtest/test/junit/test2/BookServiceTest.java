package springtest.test.junit.test2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import springtest.test.junit.test2.Book;
import springtest.test.junit.test2.BookService;
import springtest.test.junit.test2.BookStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;


class BookServiceTest {

    static BookService bookService;
    Book book;

    @BeforeAll
    static void init() {
        bookService = new BookService();
    }

    @BeforeEach
    void createBook() {
        book = new Book("title", "author");
    }

    @Test
    @DisplayName("assertAll로 test하기")
    void checkAssertAll() {
        assertAll(
                () -> assertNotEquals(BookStatus.BORROWED, book.getStatus()),
                () -> assertNotNull(book.getTitle())
        );
    }

    @Test
    @DisplayName("assumingTest")
    void doAssumingTrueTest() {
        bookService.borrowBook(book);

        assumingThat(book.getStatus() == BookStatus.BORROWED, () -> {
            bookService.returnBook(book);
            assertEquals(BookStatus.AVAILABLE, book.getStatus());
        });
    }

    @Test
    @DisplayName("Exception 테스트하기")
    void doExceptionTest() {
        book.setStatus(BookStatus.LOST);
        assertThrows(IllegalStateException.class, () -> {
            bookService.borrowBook(book);
        });
    }
}