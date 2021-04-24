package se.lexicon.aman.booklender.repository;


import org.springframework.data.repository.CrudRepository;
import se.lexicon.aman.booklender.entity.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findByReserved(boolean status);

    List<Book> findByAvailable(boolean status);

    List<Book> findByTitle(String title);

}
