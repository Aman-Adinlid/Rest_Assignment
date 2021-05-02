package se.lexicon.aman.booklender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.aman.booklender.dto.LibraryUserDto;
import se.lexicon.aman.booklender.exception.DataNotFoundException;
import se.lexicon.aman.booklender.service.LibraryUserService;

import java.util.List;

@RestController
@RequestMapping("/api/libraryUser/")
public class LibraryUserController {

    LibraryUserService libraryUserService;

    @Autowired
    public void setLibraryUserService(LibraryUserService libraryUserService) {
        this.libraryUserService = libraryUserService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryUserDto> findById(@PathVariable("id") Integer userId) {
        if (userId == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.OK).body(libraryUserService.findById(userId));
    }

    @GetMapping("/")
    public ResponseEntity<List<LibraryUserDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(libraryUserService.findAll());

    }

    @GetMapping("/email")
    public ResponseEntity<LibraryUserDto> findByEmail(@RequestParam(value = "email") String email) {
        return ResponseEntity.status(HttpStatus.OK).body(libraryUserService.findByEmail(email));
    }

    @PostMapping
    public ResponseEntity<LibraryUserDto> create(@RequestBody LibraryUserDto libraryUserDto) {
        if (libraryUserDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryUserService.create(libraryUserDto));
    }

    @PutMapping
    public ResponseEntity<LibraryUserDto> update(@RequestBody LibraryUserDto libraryUserDto) throws DataNotFoundException {
        if (libraryUserDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(libraryUserService.update(libraryUserDto));
    }


}
