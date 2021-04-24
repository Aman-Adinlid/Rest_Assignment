package se.lexicon.aman.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.aman.booklender.entity.Loan;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Long> {

    List<Loan> findByUserId(int userId);

    List<Loan> findByBookId(int bookId);

    List<Loan> findByTerminated(boolean status);

}
