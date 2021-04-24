package se.lexicon.aman.booklender.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class LoanDtoTest {
    LoanDto loanDto;
    BookDto bookDto;
    LibraryUserDto libraryUserDto;

    @BeforeEach
    public void setUp() {
        bookDto = new BookDto();
        bookDto.setTitle("The sun is also a star");
        bookDto.setAvailable(true);
        bookDto.setDescription("test");
        bookDto.setReserved(false);
        bookDto.setMaxLoanDays(20);
        bookDto.setFinePerDay(BigDecimal.ZERO);

        libraryUserDto = new LibraryUserDto();
        libraryUserDto.setName("Adam");
        libraryUserDto.setEmail("95pinkpanda@gmail.com");
        libraryUserDto.setRegDate(LocalDate.now());

        loanDto = new LoanDto();
        loanDto.setLoanTaker(libraryUserDto);
        loanDto.setBook(bookDto);
        loanDto.setLoanDate(LocalDate.of(2021, 4, 24));
        loanDto.setTerminated(true);

    }

    @Test
    @DisplayName("Test1")
    public void test1_create_LoanDto() {
        Assertions.assertEquals(libraryUserDto, loanDto.getLoanTaker());
        Assertions.assertEquals(true, loanDto.isTerminated());
    }

    @Test
    @DisplayName("Test2")
    public void test2_equal() {
        LoanDto loan = new LoanDto();
        loan.setLoanTaker(libraryUserDto);
        loan.setBook(bookDto);
        loan.setLoanDate(LocalDate.of(2021, 4, 24));
        Assertions.assertTrue(loan.equals(loan));
    }

    @Test
    @DisplayName("Test3")
    public void test3_hashCode() {
        LoanDto loan = new LoanDto();
        loan.setLoanTaker(libraryUserDto);
        loan.setBook(bookDto);
        loan.setLoanDate(LocalDate.of(2021, 4, 24));
        Assertions.assertEquals(loan.hashCode(), loan.hashCode());
    }
}
