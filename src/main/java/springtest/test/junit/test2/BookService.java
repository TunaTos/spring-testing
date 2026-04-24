package springtest.test.junit.test2;

public class BookService {

    public void borrowBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("book은 null일 수 없습니다.");
        }
        if (book.getStatus() != BookStatus.AVAILABLE) {
            throw new IllegalStateException("대출 가능한 상태가 아닙니다.");
        }
        book.setStatus(BookStatus.BORROWED);
    }

    public void returnBook(Book book) {
        book.setStatus(BookStatus.AVAILABLE);
    }

    public void lostBook(Book book) {
        book.setStatus(BookStatus.LOST);
    }

}
