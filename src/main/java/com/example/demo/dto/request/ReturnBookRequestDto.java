package com.example.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReturnBookRequestDto {

    private String isbn;
    private String email;

}
