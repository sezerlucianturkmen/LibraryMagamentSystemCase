package com.example.demo.entity;


import com.example.demo.entity.book.Book;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Borrower {
    private String email;
    private String name;
    private String phone;
    private List<Book> bookList;
}
