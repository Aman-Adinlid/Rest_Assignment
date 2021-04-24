package se.lexicon.aman.booklender.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class BookDtoTest {

    BookDto bookDto;

    @BeforeEach
    public void setUp() {
        bookDto = new BookDto();
        bookDto.setTitle("The sun is also a star");
        bookDto.setAvailable(true);
        bookDto.setDescription("test");
        bookDto.setReserved(false);
        bookDto.setMaxLoanDays(20);
        bookDto.setFinePerDay(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("Test1")
    public void test1_create_BookDto() {
        Assertions.assertEquals("The sun is also a star", bookDto.getTitle());
        Assertions.assertEquals(true, bookDto.isAvailable());
        Assertions.assertEquals("test", bookDto.getDescription());
        Assertions.assertEquals(20, bookDto.getMaxLoanDays());
        Assertions.assertEquals(BigDecimal.ZERO, bookDto.getFinePerDay());

    }

    @Test
    @DisplayName("Test2")
    public void test2_equal() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("The sun is also a star");
        bookDto.setAvailable(true);
        bookDto.setMaxLoanDays(20);
        bookDto.setFinePerDay(BigDecimal.ZERO);
        Assertions.assertTrue(bookDto.equals(bookDto));
    }

    @Test
    @DisplayName("Test3")
    public void test3_hashCode() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("The sun is also a star");
        bookDto.setAvailable(true);
        bookDto.setMaxLoanDays(20);
        bookDto.setFinePerDay(BigDecimal.ZERO);
        Assertions.assertEquals(bookDto.hashCode(), bookDto.hashCode());
    }
}
