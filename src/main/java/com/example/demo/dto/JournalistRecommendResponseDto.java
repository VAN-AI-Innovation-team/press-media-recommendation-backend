package com.example.demo.dto;

import com.example.demo.domain.Journalist;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JournalistRecommendResponseDto {
    private Long id;
    private String name;
    private String email;
    private String department;
    private String pressName;
    private double score;

    public static JournalistRecommendResponseDto of(Journalist journalist, double score) {
        return JournalistRecommendResponseDto.builder()
                .id(journalist.getId())
                .name(journalist.getName())
                .email(journalist.getEmail())
                .department(journalist.getDepartment())
                .pressName(journalist.getPress() != null ? journalist.getPress().getName() : null)
                .score(score)
                .build();
    }
}