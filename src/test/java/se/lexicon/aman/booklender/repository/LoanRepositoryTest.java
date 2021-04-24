package se.lexicon.aman.booklender.repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.aman.booklender.entity.Book;
import se.lexicon.aman.booklender.entity.LibraryUser;
import se.lexicon.aman.booklender.entity.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoanRepositoryTest {
    Book book;
    BookRepository bookRepository;
    LibraryUser libraryUser;
    LibraryUserRepository libraryUserRepository;
    Loan loan;
    LoanRepository loanRepository;

    @Autowired
    public void setLoanRepository(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setLibraryUserRepository(LibraryUserRepository libraryUserRepository) {
        this.libraryUserRepository = libraryUserRepository;
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

        libraryUser = new LibraryUser();
        libraryUser.setName("Adam");
        libraryUser.setEmail("95pinkpanda@gmail.com");
        libraryUser.setRegDate(LocalDate.now());
        libraryUserRepository.save(libraryUser);

        loan = new Loan();
        loan.setLoanTaker(libraryUser);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.of(2021, 4, 24));
        loan.setTerminated(true);
        loanRepository.save(loan);

    }

    @Test
    @DisplayName("Test1")
    public void test1_findById() {
        List<Loan> list = new ArrayList<>();
        loanRepository.findAll().iterator().forEachRemaining(list::add);
        Long expected = list.get(0).getLoanId();
        Optional<Loan> actual = loanRepository.findById(expected);
        assertEquals(libraryUser, actual.get().getLoanTaker());
    }

    @Test
    @DisplayName("Test2")
    public void test2_findAll_save() {
        List<Loan> list = new ArrayList<>();
        loanRepository.findAll().iterator().forEachRemaining(list::add);
        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("Test3")
    public void test3_delete() {
        List<Loan> list = new ArrayList<>();
        loanRepository.delete(loan);
        List<Loan> loanList = new ArrayList<>();
        loanRepository.findAll().iterator().forEachRemaining(loanList::add);
        assertEquals(list, loanList);
    }

    @Test
    @DisplayName("Test4")
    public void test4_findByLoanTakerUserId() {
        List<Loan> loanList = new ArrayList<>();
        loanRepository.findAll().iterator().forEachRemaining(loanList::add);
        assertEquals("Adam", loanRepository.findByLoanTakerUserId(1).get(0).getLoanTaker().getName());
    }

    @Test
    @DisplayName("Test5")
    public void test5_findByLoanByBook_BookId() {
        List<Loan> loanList = new ArrayList<>();
        loanRepository.findAll().iterator().forEachRemaining(loanList::add);
        assertEquals("test", loanRepository.findLoanByBook_BookId(1).get(0).getBook().getDescription());
    }

    @Test
    @DisplayName("Test6")
    public void test6_findByTerminated() {
        List<Loan> loanList = new ArrayList<>();
        loanRepository.findAll().iterator().forEachRemaining(loanList::add);
        assertEquals(1, loanRepository.findByTerminated(true).size());
    }
}
