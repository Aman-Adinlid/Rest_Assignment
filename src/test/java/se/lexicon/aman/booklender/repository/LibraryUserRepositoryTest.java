package se.lexicon.aman.booklender.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.aman.booklender.entity.LibraryUser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class LibraryUserRepositoryTest {

    LibraryUser libraryUser;
    LibraryUserRepository libraryUserRepository;

    @Autowired
    public void setLibraryUserRepository(LibraryUserRepository libraryUserRepository) {
        this.libraryUserRepository = libraryUserRepository;
    }

    @BeforeEach
    public void setUp() {
        libraryUser = new LibraryUser();
        libraryUser.setName("Adam");
        libraryUser.setEmail("95pinkpanda@gmail.com");
        libraryUser.setRegDate(LocalDate.now());

        libraryUserRepository.save(libraryUser);
    }

    @Test
    @DisplayName("Test1")
    public void test1_findById() {
        List<LibraryUser> libraryUserList = new ArrayList<>();
        libraryUserRepository.findAll().iterator().forEachRemaining(libraryUserList::add);
        Integer expected = libraryUserList.get(0).getUserId();
        Optional<LibraryUser> actual = libraryUserRepository.findById(expected);
        assertEquals("Adam", actual.get().getName());
    }

    @Test
    @DisplayName("Test2")
    public void test2_findAll_save() {
        List<LibraryUser> libraryUserList = new ArrayList<>();
        libraryUserRepository.findAll().iterator().forEachRemaining(libraryUserList::add);
        assertEquals(1, libraryUserList.size());
    }

    @Test
    @DisplayName("Test3")
    public void test3_delete() {
        List<LibraryUser> libraryUserList = new ArrayList<>();
        libraryUserRepository.delete(libraryUser);
        List<LibraryUser> list = new ArrayList<>();
        libraryUserRepository.findAll().iterator().forEachRemaining(list::add);
        assertEquals(libraryUserList, list);
    }

    @Test
    @DisplayName("Test4")
    public void test4_findByEmail() {
        LibraryUser actual = libraryUserRepository.findLibraryUserByEmailIgnoreCase("95pinkpanda@gmail.com");
        assertEquals("Adam", actual.getName());
    }
}
