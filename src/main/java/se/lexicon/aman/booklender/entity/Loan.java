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
    @JoinColumn(name = "library_user_user_id")
    private LibraryUser loanTaker;

    @ManyToOne
    @JoinColumn(name = "book_book_id")
    private Book book;
    private LocalDate loanDate;
   @Column(name = "_terminated")
    private boolean terminated;
}
