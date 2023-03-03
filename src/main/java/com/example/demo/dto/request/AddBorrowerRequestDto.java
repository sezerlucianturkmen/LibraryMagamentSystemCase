package com.example.demo.dto.request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddBorrowerRequestDto {

    private String email;
    private String name;
    private String phone;
}
