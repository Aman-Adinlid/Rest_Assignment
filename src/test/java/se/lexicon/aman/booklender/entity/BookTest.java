package se.lexicon.aman.booklender.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class BookTest {

    Book book;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setTitle("The sun is also a star");
        book.setAvailable(true);
        book.setDescription("test");
        book.setReserved(false);
        book.setMaxLoanDays(20);
        book.setFinePerDay(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("Test1")
    public void test1_create_Book() {
        Assertions.assertEquals("The sun is also a star", book.getTitle());
        Assertions.assertEquals(true, book.isAvailable());
        Assertions.assertEquals("test", book.getDescription());
        Assertions.assertEquals(20, book.getMaxLoanDays());
        Assertions.assertEquals(BigDecimal.ZERO, book.getFinePerDay());

    }

    @Test
    @DisplayName("Test2")
    public void test2_equal() {
        Book book = new Book();
        book.setTitle("The sun is also a star");
        book.setAvailable(true);
        book.setMaxLoanDays(20);
        book.setFinePerDay(BigDecimal.ZERO);
        Assertions.assertTrue(book.equals(book));
    }

    @Test
    @DisplayName("Test3")
    public void test3_hashCode() {
        Book book = new Book();
        book.setTitle("The sun is also a star");
        book.setAvailable(true);
        book.setMaxLoanDays(20);
        book.setFinePerDay(BigDecimal.ZERO);
        Assertions.assertEquals(book.hashCode(), book.hashCode());
    }
}
