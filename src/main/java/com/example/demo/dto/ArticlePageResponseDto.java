package com.example.demo.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArticlePageResponseDto {

    private List<ArticleResponseDto> items;

    private int page;

    private int limit;

    private long totalItems;

    private int totalPages;
}