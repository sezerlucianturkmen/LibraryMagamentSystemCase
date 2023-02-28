package com.example.demo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddBorrowerRequestDto {
    @Email
    @NotNull
    private String email;
    private String name;
    private String phone;
}
