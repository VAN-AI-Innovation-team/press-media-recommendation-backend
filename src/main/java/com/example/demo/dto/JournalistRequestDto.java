package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JournalistRequestDto {

    @NotBlank(message = "name은 필수값입니다.")
    private String name;

    @NotBlank(message = "email은 필수값입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotNull(message = "pressId는 필수값입니다.")
    private Long pressId;
}