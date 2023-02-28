package com.example.demo.entity.book;

import com.example.demo.entity.Borrower;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@MappedSuperclass
@SuperBuilder
public abstract class Book {
    private String isbn;
    private String title;
    private String author;
    private Integer availableCopies;
    private Type type;
    private List<Borrower> borrowerList;


}
