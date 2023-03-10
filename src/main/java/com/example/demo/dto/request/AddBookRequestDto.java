package com.example.demo.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddBookRequestDto {

    private String isbn;
    private String title;
    private String author;
    private Integer availableCopies;
    private String type;
}
