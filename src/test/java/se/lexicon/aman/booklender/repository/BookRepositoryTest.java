package se.lexicon.aman.booklender.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.aman.booklender.entity.Book;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookRepositoryTest {
    Book book;
    BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setTitle("The sun is also a star");
        book.setAvailable(true);
        book.setDescription("test");
        book.setReserved(false);
        book.setMaxLoanDays(20);
        book.setFinePerDay(BigDecimal.ZERO);

        bookRepository.save(book);

    }

    @Test
    @DisplayName("Test1")
    public void test1_findById() {
        List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(bookList::add);
        Integer expected = bookList.get(0).getBookId();
        Optional<Book> actual = bookRepository.findById(expected);
        assertEquals("The sun is also a star", actual.get().getTitle());
    }

    @Test
    @DisplayName("Test2")
    public void test2_findAll_save() {
        List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(bookList::add);
        assertEquals(1, bookList.size());
    }

    @Test
    @DisplayName("Test3")
    public void test3_delete() {
        List<Book> bookList = new ArrayList<>();
        bookRepository.delete(book);
        List<Book> list = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(list::add);
        assertEquals(bookList, list);
    }

    @Test
    @DisplayName("Test4")
    public void test4_findByReserved() {
        assertEquals(20, bookRepository.findByReserved(false).get(0).getMaxLoanDays());
    }

    @Test
    @DisplayName("Test5")
    public void test5_findByAvailable() {
        assertEquals(20, bookRepository.findByAvailable(true).get(0).getMaxLoanDays());
    }

    @Test
    @DisplayName("Test6")
    public void test6_findByTitle() {
        assertEquals(20, bookRepository.findByTitle("The sun is also a star").get(0).getMaxLoanDays());
    }
}
