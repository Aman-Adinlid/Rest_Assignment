package se.lexicon.aman.booklender.service;

import se.lexicon.aman.booklender.dto.LibraryUserDto;
import se.lexicon.aman.booklender.exception.RecordNotFoundException;

import java.util.List;

public interface LibraryUserService {

    LibraryUserDto findById(int userId) throws RecordNotFoundException;

    LibraryUserDto findByEmail(String email);

    List<LibraryUserDto> findAll();

    LibraryUserDto create(LibraryUserDto libraryUserDto);

    LibraryUserDto update(LibraryUserDto libraryUserDto) throws RecordNotFoundException;

    void delete(int userId) throws RecordNotFoundException;


}
