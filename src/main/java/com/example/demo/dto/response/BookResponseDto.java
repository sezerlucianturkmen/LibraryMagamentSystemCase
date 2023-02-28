package com.example.demo.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookResponseDto {
    private String isbn;
    private String title;
    private String author;
    private Integer availableCopies;
    private String type;
}
