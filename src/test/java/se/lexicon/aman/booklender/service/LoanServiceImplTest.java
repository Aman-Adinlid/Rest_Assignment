package se.lexicon.aman.booklender.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.aman.booklender.dto.BookDto;
import se.lexicon.aman.booklender.dto.LibraryUserDto;
import se.lexicon.aman.booklender.dto.LoanDto;
import se.lexicon.aman.booklender.exception.DataNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoanServiceImplTest {
    LoanService loanService;
    LoanDto loanDto;
    LoanDto dto;

    BookService bookService;
    BookDto bookDto;

    LibraryUserService libraryUserService;
    LibraryUserDto libraryUserDto;

    @Autowired
    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setLibraryUserService(LibraryUserService libraryUserService) {
        this.libraryUserService = libraryUserService;
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
      //create it
        bookService.create(bookDto);

        libraryUserDto = new LibraryUserDto();
        libraryUserDto.setName("Adam");
        libraryUserDto.setEmail("95pinkpanda@gmail.com");
        libraryUserDto.setRegDate(LocalDate.now());

        libraryUserService.create(libraryUserDto);

        loanDto = new LoanDto();
        loanDto.setLoanTaker(libraryUserService.findAll().get(0));
        loanDto.setBook(bookService.findAll().get(0));
        loanDto.setLoanDate(LocalDate.of(2021, 4, 24));
        loanDto.setTerminated(true);

        loanService.create(loanDto);

        dto = new LoanDto();
        dto.setLoanTaker(libraryUserService.findAll().get(0));
        dto.setBook(bookService.findAll().get(0));
        dto.setLoanDate(LocalDate.of(2022, 2, 27));
        dto.setTerminated(false);
    }

    @Test
    @DisplayName("Test1 ")
    public void test1_findById() throws DataNotFoundException {
        assertEquals(1, loanService.findById(1).getLoanId());
    }

    @Test
    @DisplayName("Test2 ")
    public void test2_findByBookId() {
        loanService.create(loanDto);
        assertEquals("test", loanService.findByBook(1).get(0).getBook().getDescription());

    }

    @Test
    @DisplayName("Test3 ")
    public void test3_findByUserId() {
        assertEquals("95pinkpanda@gmail.com", loanService.findByUserId(1).get(0).getLoanTaker().getEmail());
    }

    @Test
    @DisplayName("Test4 ")
    public void test4_findByTerminated() {
        assertEquals(bookService.findAll().get(0), loanService.findByTerminated(true).get(0).getBook());
    }


    @Test
    @DisplayName("Test5 ")
    public void test5_findAll() {
        assertEquals(libraryUserService.findAll().get(0), loanService.findAll().get(0).getLoanTaker());
    }

    @Test
    @DisplayName("Test6 ")
    public void test6_create() {
        assertEquals(libraryUserService.findAll().get(0), loanService.create(loanDto).getLoanTaker());
    }

    @Test
    @DisplayName("Test7 ")
    public void test7_delete() throws DataNotFoundException {
        loanService.create(loanDto);
        loanService.delete(1);
        assertEquals(1, loanService.findAll().size());
    }


}