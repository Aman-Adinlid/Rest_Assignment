package se.lexicon.aman.booklender.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class LibraryUserDtoTest {
    LibraryUserDto libraryUserDto;



    @BeforeEach
    public void setUp() {
        libraryUserDto = new LibraryUserDto();
        libraryUserDto.setName("Adam");
        libraryUserDto.setEmail("95pinkpanda@gmail.com");
        libraryUserDto.setRegDate(LocalDate.now());
    }

    @Test
    @DisplayName("Test1")
    public void test1_create_LibraryUser() {
        Assertions.assertEquals("Adam", libraryUserDto.getName());
        Assertions.assertEquals("95pinkpanda@gmail.com", libraryUserDto.getEmail());
        Assertions.assertEquals(LocalDate.now(), libraryUserDto.getRegDate());
    }
    @Test
    @DisplayName("Test2")
    public void test2_equal() {
        LibraryUserDto user = new LibraryUserDto();
        user.setName("Adam");
        user.setEmail("95pinkpanda@gmail.com");
        user.setRegDate(LocalDate.now());
        Assertions.assertTrue(libraryUserDto.equals(user));
    }

    @Test
    @DisplayName("Test3")
    public void test3_hashCode() {
        LibraryUserDto user = new LibraryUserDto();
        user.setName("Adam");
        user.setEmail("95pinkpanda@gmail.com");
        user.setRegDate(LocalDate.now());
        Assertions.assertEquals(user.hashCode(), libraryUserDto.hashCode());
    }
}

