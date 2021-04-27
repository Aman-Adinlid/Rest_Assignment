package se.lexicon.aman.booklender.service;

import se.lexicon.aman.booklender.dto.LoanDto;

import java.util.List;

public interface LoanService {

    LoanDto findById(Long loanId);

    List<LoanDto> findByBook(int bookId);

    List<LoanDto> findByUserId(int userId);

    List<LoanDto> findByTerminated(boolean terminated);

    List<LoanDto> findAll();

    LoanDto create(LoanDto loanDto);

    LoanDto update(LoanDto loanDto);

    boolean delete(int loanId);
}
