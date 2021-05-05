package se.lexicon.aman.booklender.service;

import se.lexicon.aman.booklender.dto.BookDto;
import se.lexicon.aman.booklender.exception.RecordNotFoundException;

import java.util.List;

public interface BookService {
    List<BookDto> findByReserved(boolean reserved);

    List<BookDto> findByAvailable(boolean available);

    List<BookDto> findByTitle(String title);

    BookDto findById(int bookId) throws RecordNotFoundException;

    List<BookDto> findAll();

    BookDto create(BookDto bookDto);

    BookDto update(BookDto bookDto) throws RecordNotFoundException;

    void delete(int bookId) throws RecordNotFoundException;

}
