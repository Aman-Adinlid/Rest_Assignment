package se.lexicon.aman.booklender.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
@Data
public class BookDto {
    private int bookId;
    @NotEmpty(message = "Category Name cannot be empty")
    @Size(min = 2, max = 30, message = "Category Name should be in 2-30 chars range")
    private String title;
    private boolean available;
    private boolean reserved;
    private int maxLoanDays;
    private BigDecimal finePerDay;
    private String description;
}
