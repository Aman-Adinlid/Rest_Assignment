package se.lexicon.aman.booklender.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.aman.booklender.dto.BookDto;
import se.lexicon.aman.booklender.entity.Book;
import se.lexicon.aman.booklender.exception.DataNotFoundException;
import se.lexicon.aman.booklender.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    ModelMapper modelMapper;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookDto> findByReserved(boolean reserved) {
        return bookRepository.findByReserved(reserved).stream().
                map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findByAvailable(boolean available) {
        return bookRepository.findByAvailable(available).stream().
                map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findByTitle(String title) {
        return bookRepository.findByTitle(title).stream().map(book ->
                modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    @Override
    public BookDto findById(int bookId) throws DataNotFoundException {
        return modelMapper.map(bookRepository.findById(bookId).orElseThrow(() ->
                new DataNotFoundException("BookDto not found")), BookDto.class);
    }

    @Override
    public List<BookDto> findAll() {
        List<Book> list = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(list::add);
        return list.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    @Override
    public BookDto create(BookDto bookDto) {
        return modelMapper.map(bookRepository.save(modelMapper.map(bookDto, Book.class)), BookDto.class);
    }

    @Transactional
    @Override
    public BookDto update(BookDto bookDto) throws DataNotFoundException {
        if (bookDto == null) throw new IllegalArgumentException("BookDto object should not be null");
        if (bookDto.getBookId() < 1) throw new IllegalArgumentException("BookId should not be null");
        Optional<Book> bookOptional = bookRepository.findById(bookDto.getBookId());
        if (bookOptional.isPresent()) {
            return modelMapper.map(bookRepository.save(modelMapper.map(bookDto, Book.class)), BookDto.class);
        } else {
            throw new DataNotFoundException("BookDto not found");
        }

    }

    @Override
    public void delete(int bookId) throws DataNotFoundException {
        if (bookId < 1) throw new IllegalArgumentException("Id is not valid");
        bookRepository.delete(modelMapper.map(bookRepository.findById(bookId)
                .orElseThrow(() -> new DataNotFoundException("Id ")), Book.class));
    }
}

