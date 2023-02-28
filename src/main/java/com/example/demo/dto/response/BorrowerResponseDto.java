package com.example.demo.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BorrowerResponseDto {
    private String email;
    private String name;
    private String phone;
}
