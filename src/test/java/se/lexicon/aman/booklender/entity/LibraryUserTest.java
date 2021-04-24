package se.lexicon.aman.booklender.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class LibraryUserTest {

    LibraryUser libraryUser;


    @BeforeEach
    public void setUp() {
        libraryUser = new LibraryUser();
        libraryUser.setName("Adam");
        libraryUser.setEmail("95pinkpanda@gmail.com");
        libraryUser.setRegDate(LocalDate.now());

    }

    @Test
    @DisplayName("Test1")
    public void test1_create_LibraryUser() {
        Assertions.assertEquals("Adam", libraryUser.getName());
        Assertions.assertEquals("95pinkpanda@gmail.com", libraryUser.getEmail());
        Assertions.assertEquals(LocalDate.now(), libraryUser.getRegDate());

    }

    @Test
    @DisplayName("Test2")
    public void test2_equal() {
        LibraryUser user = new LibraryUser();
        user.setName("Adam");
        user.setEmail("95pinkpanda@gmail.com");
        user.setRegDate(LocalDate.now());
        Assertions.assertTrue(libraryUser.equals(user));
    }

    @Test
    @DisplayName("Test3")
    public void test3_hashCode() {
        LibraryUser user = new LibraryUser();
        user.setName("Adam");
        user.setEmail("95pinkpanda@gmail.com");
        user.setRegDate(LocalDate.now());
        Assertions.assertEquals(user.hashCode(), libraryUser.hashCode());
    }
}
