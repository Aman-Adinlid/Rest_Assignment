package se.lexicon.aman.booklender.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.aman.booklender.dto.BookDto;
import se.lexicon.aman.booklender.exception.DataNotFoundException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookServiceImplTest {
    BookService bookService;
    BookDto bookDto;
    BookDto dto;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @BeforeEach
    public void setUp() {
        bookDto = new BookDto();
        bookDto.setTitle("The sun is also a star");
        bookDto.setAvailable(true);
        bookDto.setDescription("test");
        bookDto.setReserved(false);
        bookDto.setMaxLoanDays(20);
        bookDto.setFinePerDay(BigDecimal.ZERO);

        dto = new BookDto();
        dto.setTitle("test");
        dto.setAvailable(true);
        dto.setDescription("test2");

        bookService.create(bookDto);

    }

    @Test
    @DisplayName("Test1 ")
    public void test1_findByReserved() {
        assertEquals("The sun is also a star", bookService.findByReserved(false).get(0).getTitle());
    }

    @Test
    @DisplayName("Test2 ")
    public void test2_findByAvailable() {
        assertEquals("test", bookService.findByAvailable(true).get(0).getDescription());
    }


    @Test
    @DisplayName("Test3 ")
    public void test3_finByTitle() {
        assertEquals("The sun is also a star", bookService.findByTitle("The sun is also a star").get(0).getTitle());

    }

    @Test
    @DisplayName("Test4 ")
    public void test4_findById() throws DataNotFoundException {
        assertEquals(1, bookService.findById(1).getBookId());
    }

    @Test
    @DisplayName("Test5 ")
    public void test5_findAll() {
        assertEquals("test", bookService.findAll().get(0).getDescription());
    }

    @Test
    @DisplayName("Test6 ")
    public void test6_create() {
        assertEquals("test", bookService.create(dto).getTitle());
    }

    @Test
    @DisplayName("Test7 ")
    public void test7_update() throws DataNotFoundException {
        bookDto.setBookId(1);
        assertEquals(20, bookService.update(bookDto).getMaxLoanDays());
    }

    @Test
    @DisplayName("Test8 ")
    public void test8_delete() throws DataNotFoundException {
        bookService.create(bookDto);
        bookService.delete(1);
        assertEquals(1, bookService.findAll().size());
    }


}
