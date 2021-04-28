package se.lexicon.aman.booklender.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import se.lexicon.aman.booklender.dto.LibraryUserDto;
import se.lexicon.aman.booklender.entity.LibraryUser;
import se.lexicon.aman.booklender.exception.DataNotFoundException;
import se.lexicon.aman.booklender.repository.LibraryUserRepository;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryUserServiceImpl implements LibraryUserService {
    LibraryUserRepository libraryUserRepository;
    ModelMapper modelMapper;

    @Autowired
    public void setLibraryUserRepository(LibraryUserRepository libraryUserRepository) {
        this.libraryUserRepository = libraryUserRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public LibraryUserDto findById(int userId) throws DataAccessException {
        return modelMapper.map(libraryUserRepository.findById(userId).orElseThrow(() ->
                new DateTimeException("LibraryUserDto not found")), LibraryUserDto.class);
    }

    @Override
    public LibraryUserDto findByEmail(String email) {
        return modelMapper.map(libraryUserRepository.findLibraryUserByEmailIgnoreCase(email), LibraryUserDto.class);
    }

    @Override
    public List<LibraryUserDto> findAll() {
        List<LibraryUser> libraryUserList = new ArrayList<>();
        libraryUserRepository.findAll().iterator().forEachRemaining(libraryUserList::add);
        return libraryUserList.stream().map(libraryUser ->
                modelMapper.map(libraryUser, LibraryUserDto.class)).collect(Collectors.toList());
    }

    @Override
    public LibraryUserDto create(LibraryUserDto libraryUserDto) {
        return modelMapper.map(libraryUserRepository.save(modelMapper.map(libraryUserDto, LibraryUser.class)), LibraryUserDto.class);
    }


    @Override
    public LibraryUserDto update(LibraryUserDto libraryUserDto) throws DataAccessException, DataNotFoundException {
        if (libraryUserDto == null) throw new IllegalArgumentException("LibraryUserDto object should not be null");
        if (libraryUserDto.getUserId() < 1) throw new IllegalArgumentException("Id should not be null");
        Optional<LibraryUser> optionalLibraryUser = libraryUserRepository.findById(modelMapper.map(libraryUserDto, LibraryUser.class).getUserId());
        if (optionalLibraryUser.isPresent()) {
            return modelMapper.map(libraryUserRepository.save(modelMapper.map(libraryUserDto, LibraryUser.class)), LibraryUserDto.class);
        } else throw new DataNotFoundException("LibraryUserDto not found");
    }


    @Override
    public void delete(int userId) throws DataAccessException, DataNotFoundException {
        if (userId < 1) throw new IllegalArgumentException("Id is not valid");
        libraryUserRepository.delete(modelMapper.map(libraryUserRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("Id ")), LibraryUser.class));

    }

}
