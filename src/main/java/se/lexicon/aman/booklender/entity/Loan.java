package se.lexicon.aman.booklender.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loanId;
    @ManyToOne
    private LibraryUser loanTaker;
    private LocalDate loanDate;
    private boolean terminated;
}
