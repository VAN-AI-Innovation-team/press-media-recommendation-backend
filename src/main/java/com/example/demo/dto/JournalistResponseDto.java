package com.example.demo.dto;

import com.example.demo.domain.Journalist;
import lombok.Getter;

@Getter
public class JournalistResponseDto {
    private Long id;
    private String name;
    private String email;
    private String pressName; // 언론사 이름

    public JournalistResponseDto(Journalist journalist) {
        this.id = journalist.getId();
        this.name = journalist.getName();
        this.email = journalist.getEmail();
        this.pressName = (journalist.getPress() != null) ? journalist.getPress().getName() : null;
    }
}