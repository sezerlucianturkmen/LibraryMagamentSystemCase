package com.example.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddBookRequestDto {
    @NotNull
    private String isbn;
    private String title;
    private String author;
    private Integer availableCopies;
    private String type;
}
