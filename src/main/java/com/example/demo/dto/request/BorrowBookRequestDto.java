package com.example.demo.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BorrowBookRequestDto {

    private String isbn;

    private String email;

}
