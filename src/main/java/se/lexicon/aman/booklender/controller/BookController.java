package se.lexicon.aman.booklender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.aman.booklender.dto.BookDto;
import se.lexicon.aman.booklender.exception.RecordNotFoundException;
import se.lexicon.aman.booklender.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/book/")
public class BookController {

    BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable("id") Integer bookId) throws RecordNotFoundException {
        if (bookId == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(bookId));
    }

    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto) {
        if (bookDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(bookDto));
    }

    @PutMapping
    public ResponseEntity<BookDto> update(@RequestBody BookDto bookDto) throws RecordNotFoundException {
        if (bookDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookService.update(bookDto));
    }

    @GetMapping("/find")
    public ResponseEntity<List<BookDto>> find(@RequestParam(value = "title", required = false) String title,
                                              @RequestParam(value = "available", required = false) boolean available,
                                              @RequestParam(value = "reserved", required = false) boolean reserved) {
        List<BookDto> findByTitle = bookService.findByTitle(title);
        List<BookDto> findByAvailable = bookService.findByAvailable(available);
        List<BookDto> findByReserved = bookService.findByReserved(reserved);
        if (title != null) {
            return ResponseEntity.ok(findByTitle);
        }
        if (available) {
            return ResponseEntity.ok(findByAvailable);
        }
        if (reserved)
            return ResponseEntity.ok(findByReserved);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
