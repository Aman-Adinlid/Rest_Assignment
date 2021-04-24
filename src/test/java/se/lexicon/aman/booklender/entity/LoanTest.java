package se.lexicon.aman.booklender.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class LoanTest {
    Loan loan;
    Book book;
    LibraryUser libraryUser;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setTitle("The sun is also a star");
        book.setAvailable(true);
        book.setDescription("test");
        book.setReserved(false);
        book.setMaxLoanDays(20);
        book.setFinePerDay(BigDecimal.ZERO);

        libraryUser = new LibraryUser();
        libraryUser.setName("Adam");
        libraryUser.setEmail("95pinkpanda@gmail.com");
        libraryUser.setRegDate(LocalDate.now());


        loan = new Loan();
        loan.setLoanTaker(libraryUser);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.of(2021, 4, 24));
        loan.setTerminated(true);
    }

    @Test
    @DisplayName("Test1")
    public void test1_create_Loan() {
        Assertions.assertEquals(libraryUser, loan.getLoanTaker());
        Assertions.assertEquals(true, loan.isTerminated());
    }

    @Test
    @DisplayName("Test2")
    public void test2_equal() {
        Loan loan = new Loan();
        loan.setLoanTaker(libraryUser);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.of(2021, 4, 24));
        Assertions.assertTrue(loan.equals(loan));
    }

    @Test
    @DisplayName("Test3")
    public void test3_hashCode() {
        Loan loan = new Loan();
        loan.setLoanTaker(libraryUser);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.of(2021, 4, 24));
        Assertions.assertEquals(loan.hashCode(), loan.hashCode());
    }
}
