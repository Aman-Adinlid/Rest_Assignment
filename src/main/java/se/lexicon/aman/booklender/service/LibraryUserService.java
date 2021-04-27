package se.lexicon.aman.booklender.service;

import org.springframework.dao.DataAccessException;
import se.lexicon.aman.booklender.dto.LibraryUserDto;

import java.util.List;

public interface LibraryUserService {
    LibraryUserDto findById(int userId) throws DataAccessException;

    LibraryUserDto findByEmail(String email);

    List<LibraryUserDto> findAll();

    LibraryUserDto create(LibraryUserDto libraryUserDto);

    LibraryUserDto update(LibraryUserDto libraryUserDto) throws DataAccessException;

    boolean delete(int userId) throws DataAccessException;


}
