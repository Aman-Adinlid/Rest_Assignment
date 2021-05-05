package se.lexicon.aman.booklender.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.aman.booklender.dto.LibraryUserDto;
import se.lexicon.aman.booklender.exception.RecordNotFoundException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LibraryUserServiceImplTest {
    LibraryUserService libraryUserService;
    LibraryUserDto libraryUserDto;
    LibraryUserDto userDto;

    @Autowired
    public void setLibraryUserService(LibraryUserService libraryUserService) {
        this.libraryUserService = libraryUserService;
    }

    @BeforeEach
    public void setUp() {
        libraryUserDto = new LibraryUserDto();
        libraryUserDto.setName("Adam");
        libraryUserDto.setEmail("95pinkpanda@gmail.com");
        libraryUserDto.setRegDate(LocalDate.now());

        userDto = new LibraryUserDto();
        userDto.setRegDate(LocalDate.now());
        userDto.setName("Aman");
        userDto.setEmail("amanl2@gmail.com");
        libraryUserService.create(libraryUserDto);
    }

    @Test
    @DisplayName("Test1 ")
    public void test1_findById() throws RecordNotFoundException {
        assertEquals("Adam", libraryUserService.findById(1).getName());
    }

    @Test
    @DisplayName("Test2 ")
    public void test2_findByEmail() {
        assertEquals("95pinkpanda@gmail.com", libraryUserService.findByEmail("95pinkpanda@gmail.com").getEmail());
    }

    @Test
    @DisplayName("Test3 ")
    public void test3_finAll() {
        assertEquals("Adam", libraryUserService.findAll().get(0).getName());
        assertEquals("95pinkpanda@gmail.com", libraryUserService.findAll().get(0).getEmail());
    }

    @Test
    @DisplayName("Test4 ")
    public void test4_create() {
        assertEquals("Aman", libraryUserService.create(userDto).getName());
    }

    @Test
    @DisplayName("Test5 ")
    public void test5_update() throws RecordNotFoundException {
        userDto.setUserId(1);
        assertEquals("Aman", libraryUserService.update(userDto).getName());
    }

    @Test
    @DisplayName("Test6 ")
    public void test6_delete() throws RecordNotFoundException{
        libraryUserService.create(userDto);
        libraryUserService.delete(1);
        assertEquals(1, libraryUserService.findAll().size());

    }

}
