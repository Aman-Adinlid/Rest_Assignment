package se.lexicon.aman.booklender.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.aman.booklender.dto.LoanDto;
import se.lexicon.aman.booklender.entity.Loan;
import se.lexicon.aman.booklender.exception.DataNotFoundException;
import se.lexicon.aman.booklender.repository.BookRepository;
import se.lexicon.aman.booklender.repository.LibraryUserRepository;
import se.lexicon.aman.booklender.repository.LoanRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {
    LoanRepository loanRepository;
    ModelMapper modelMapper;
    BookRepository bookRepository;
    LibraryUserRepository libraryUserRepository;

    @Autowired
    public void setLoanRepository(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setLibraryUserRepository(LibraryUserRepository libraryUserRepository) {
        this.libraryUserRepository = libraryUserRepository;
    }

    @Override
    public LoanDto findById(long loanId) throws DataNotFoundException {
        if (loanId == 0) throw new IllegalArgumentException("Id should not be empty");
        return modelMapper.map(loanRepository.findById(loanId).orElseThrow(() ->
                new DataNotFoundException("LoanDto not found")), LoanDto.class);
    }

    @Override
    public List<LoanDto> findByBook(int bookId) {
        if (bookId < 1) throw new IllegalArgumentException("bookId should not be null");
        List<Loan> loanList = new ArrayList<>();
        loanRepository.findLoanByBook_BookId(bookId).iterator().forEachRemaining(loanList::add);
        List<LoanDto> loanDtoList = loanRepository.findLoanByBook_BookId(bookId)
                .stream().map(book -> modelMapper.map(book, LoanDto.class)).collect(Collectors.toList());
        return loanDtoList;
    }

    @Override
    public List<LoanDto> findByUserId(int userId) {
        if (userId < 1) throw new IllegalArgumentException("The field is empty");
        List<Loan> loanList = new ArrayList<>();
        loanRepository.findByLoanTakerUserId(userId).iterator().forEachRemaining(loanList::add);

        List<LoanDto> loanDtoList = loanRepository.findByLoanTakerUserId(userId)
                .stream().map(book -> modelMapper.map(book, LoanDto.class)).collect(Collectors.toList());
        return loanDtoList;
    }

    @Override
    public List<LoanDto> findByTerminated(boolean terminated) {
        return loanRepository.findByTerminated(terminated)
                .stream().map(loan -> modelMapper.map(loan, LoanDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<LoanDto> findAll() {
        List<Loan> list = new ArrayList<>();
        loanRepository.findAll().iterator().forEachRemaining(list::add);
        return list.stream().map(loan -> modelMapper.map(loan, LoanDto.class)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public LoanDto create(LoanDto loanDto) {
        return modelMapper.map(loanRepository.save(modelMapper.map(loanDto, Loan.class)), LoanDto.class);

    }

    @Transactional
    @Override
    public LoanDto update(LoanDto loanDto) throws DataNotFoundException {
        if (loanDto == null) throw new IllegalArgumentException("BookDto object should not be null");
        if (loanDto.getLoanId() < 1) throw new IllegalArgumentException("BookId should not be null");
        Optional<Loan> optionalLoan = loanRepository.findById(loanDto.getLoanId());
        if (optionalLoan.isPresent()) {
            return modelMapper.map(loanRepository.save(modelMapper.map(loanDto, Loan.class)), LoanDto.class);
        } else {
            throw new DataNotFoundException("LoanDto not found");
        }
    }

    @Override
    public void delete(long loanId) throws DataNotFoundException {
        if (loanId < 1) throw new IllegalArgumentException("Id is not valid");
        loanRepository.delete(modelMapper.map(loanRepository.findById(loanId)
                .orElseThrow(() -> new DataNotFoundException("Id ")), Loan.class));
    }
}
