package com.example.demo.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Article;
import com.example.demo.domain.repository.ArticleRepository;
import com.example.demo.dto.ArticlePageResponseDto;
import com.example.demo.dto.ArticleResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticlePageResponseDto getArticlePage(
            int page,
            int limit,
            String language
    ) {

        int safePage = Math.max(page, 1);
        int safeLimit = Math.max(limit, 1);

        Pageable pageable =
                PageRequest.of(
                        safePage - 1,
                        safeLimit
                );

        Page<Article> articlePage =
                articleRepository.findAll(pageable);

        List<ArticleResponseDto> items =
                articlePage.getContent()
                        .stream()
                        .map(article ->
                                ArticleResponseDto.from(
                                        article,
                                        language
                                )
                        )
                        .collect(Collectors.toList());

        return ArticlePageResponseDto.builder()
                .items(items)
                .page(safePage)
                .limit(safeLimit)
                .totalItems(
                        articlePage.getTotalElements()
                )
                .totalPages(
                        Math.max(
                                1,
                                articlePage.getTotalPages()
                        )
                )
                .build();
    }

    public List<ArticleResponseDto> getAllArticles(
            String language
    ) {

        return articleRepository.findAll()
                .stream()
                .map(article ->
                        ArticleResponseDto.from(
                                article,
                                language
                        )
                )
                .collect(Collectors.toList());
    }

    public ArticleResponseDto getArticleById(
            Long articleId,
            String language
    ) {

        Article article =
                articleRepository
                        .findById(articleId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "존재하지 않는 기사입니다. ID: "
                                                + articleId
                                )
                        );

        return ArticleResponseDto.from(
                article,
                language
        );
    }

    public List<ArticleResponseDto> getRelatedArticles(
            Long articleId,
            int limit,
            String language
    ) {

        Article currentArticle =
                articleRepository
                        .findById(articleId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "존재하지 않는 기사입니다. ID: "
                                                + articleId
                                )
                        );

        Set<String> currentTopics =
                Arrays.stream(
                                currentArticle
                                        .getCategory()
                                        .split("·")
                        )
                        .map(String::trim)
                        .collect(Collectors.toSet());

        return articleRepository.findAll()
                .stream()
                .filter(article ->
                        !article.getId()
                                .equals(articleId)
                )
                .filter(article ->
                        article.getCategory() != null
                )
                .sorted(
                        Comparator
                                .comparingInt(
                                        (Article article) ->
                                                calculateRelevance(
                                                        article,
                                                        currentTopics
                                                )
                                )
                                .reversed()
                                .thenComparing(
                                        Article::getId,
                                        Comparator.reverseOrder()
                                )
                )
                .limit(Math.max(limit, 1))
                .map(article ->
                        ArticleResponseDto.from(
                                article,
                                language
                        )
                )
                .collect(Collectors.toList());
    }

    private int calculateRelevance(
            Article article,
            Set<String> currentTopics
    ) {

        if (article.getCategory() == null) {
            return 0;
        }

        return (int) Arrays.stream(
                        article
                                .getCategory()
                                .split("·")
                )
                .map(String::trim)
                .filter(currentTopics::contains)
                .count();
    }
}