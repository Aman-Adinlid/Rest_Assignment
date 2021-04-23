package se.lexicon.aman.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.aman.booklender.entity.LibraryUser;

import java.util.List;

public interface LibraryUserRepository extends CrudRepository<LibraryUser, Integer> {

   List<LibraryUser> findLibraryUserByEmailIgnoreCase(String email);

}
