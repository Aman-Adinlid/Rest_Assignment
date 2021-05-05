package se.lexicon.aman.booklender.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Data
public class LibraryUserDto {
    private int userId;
    private LocalDate regDate;
    @NotNull
    @Size(min = 2, max = 30, message = "Category Name should be in 2-30 chars range")
    private String name;
    private String email;
}
