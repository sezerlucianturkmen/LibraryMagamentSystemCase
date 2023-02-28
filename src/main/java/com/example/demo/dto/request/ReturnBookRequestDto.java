package com.example.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReturnBookRequestDto {
    @NotNull
    private String isbn;
    @NotNull
    private String email;

}
