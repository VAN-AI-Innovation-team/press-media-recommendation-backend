package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ArticlePageResponseDto;
import com.example.demo.dto.ArticleResponseDto;
import com.example.demo.service.ArticleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/page")
    public ResponseEntity<ArticlePageResponseDto>
    getArticlePage(

            @RequestParam(defaultValue = "1")
            int page,

            @RequestParam(defaultValue = "3")
            int limit,

            @RequestParam(defaultValue = "ko")
            String language
    ) {

        return ResponseEntity.ok(
                articleService.getArticlePage(
                        page,
                        limit,
                        language
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<ArticleResponseDto>>
    getAllArticles(

            @RequestParam(defaultValue = "ko")
            String language
    ) {

        return ResponseEntity.ok(
                articleService.getAllArticles(
                        language
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDto>
    getArticleById(

            @PathVariable
            Long id,

            @RequestParam(defaultValue = "ko")
            String language
    ) {

        return ResponseEntity.ok(
                articleService.getArticleById(
                        id,
                        language
                )
        );
    }

    @GetMapping("/{id}/related")
    public ResponseEntity<List<ArticleResponseDto>>
    getRelatedArticles(

            @PathVariable
            Long id,

            @RequestParam(defaultValue = "3")
            int limit,

            @RequestParam(defaultValue = "ko")
            String language
    ) {

        return ResponseEntity.ok(
                articleService.getRelatedArticles(
                        id,
                        limit,
                        language
                )
        );
    }
}