package com.example.demo.service;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Journalist;
import com.example.demo.domain.JournalistStatus;
import com.example.demo.domain.repository.JournalistRepository;
import com.example.demo.dto.JournalistRecommendResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JournalistRecommendService {

    private final JournalistRepository journalistRepository;

    /**
     * 카테고리 및 키워드 기반 기자 추천
     *
     * 추천 점수
     * - 카테고리 일치: 40점
     * - 키워드 일치: 최대 40점
     * - 이메일 존재: 20점
     * - 총점: 최대 100점
     */
    public List<JournalistRecommendResponseDto> recommendJournalists(
            String category,
            List<String> keywords) {

        List<Journalist> activeJournalists =
                journalistRepository.findByStatus(JournalistStatus.ACTIVE);

        return activeJournalists.stream()
                .map(journalist ->
                        new AbstractMap.SimpleEntry<>(
                                journalist,
                                calculateScore(journalist, category, keywords)
                        )
                )
                .filter(entry -> entry.getValue() > 0)
                .sorted(
                        (e1, e2) -> {
                            int scoreCompare =
                                    Double.compare(
                                            e2.getValue(),
                                            e1.getValue()
                                    );

                            // 점수가 같으면 기자 이름순
                            if (scoreCompare != 0) {
                                return scoreCompare;
                            }

                            String name1 = e1.getKey().getName();
                            String name2 = e2.getKey().getName();

                            if (name1 == null) {
                                return 1;
                            }

                            if (name2 == null) {
                                return -1;
                            }

                            return name1.compareTo(name2);
                        }
                )
                .map(entry ->
                        JournalistRecommendResponseDto.of(
                                entry.getKey(),
                                entry.getValue()
                        )
                )
                .collect(Collectors.toList());
    }

    /**
     * 기자 한 명의 추천 점수를 계산한다.
     */
    private double calculateScore(
            Journalist journalist,
            String category,
            List<String> keywords) {

        double score = 0.0;

        // 1. 카테고리 일치: 40점
        if (isCategoryMatched(journalist, category)) {
            score += 40.0;
        }

        // 2. 키워드 일치: 최대 40점
        score += calculateKeywordScore(journalist, keywords);

        // 3. 이메일 존재: 20점
        if (journalist.getEmail() != null
                && !journalist.getEmail().isBlank()) {
            score += 20.0;
        }

        // 최대 100점
        score = Math.min(score, 100.0);

        // 소수점 첫째 자리 반올림
        return Math.round(score * 10.0) / 10.0;
    }

    /**
     * 기자의 부서와 요청 카테고리를 비교한다.
     */
    private boolean isCategoryMatched(
            Journalist journalist,
            String category) {

        if (category == null || category.isBlank()) {
            return false;
        }

        if (journalist.getDepartment() == null
                || journalist.getDepartment().isBlank()) {
            return false;
        }

        return journalist.getDepartment()
                .trim()
                .equalsIgnoreCase(category.trim());
    }

    /**
     * 키워드 일치 점수를 계산한다.
     *
     * 최대 40점이며,
     * 입력된 키워드 중 기자의 최근 기사 키워드와
     * 일치하는 비율을 기준으로 계산한다.
     */
    private double calculateKeywordScore(
            Journalist journalist,
            List<String> keywords) {

        if (journalist.getRecentArticleKeywords() == null
                || journalist.getRecentArticleKeywords().isBlank()) {
            return 0.0;
        }

        if (keywords == null || keywords.isEmpty()) {
            return 0.0;
        }

        List<String> journalistKeywords =
                Arrays.stream(
                                journalist.getRecentArticleKeywords()
                                        .split(",")
                        )
                        .map(String::trim)
                        .filter(keyword -> !keyword.isBlank())
                        .map(keyword ->
                                keyword.toLowerCase(Locale.ROOT)
                        )
                        .distinct()
                        .collect(Collectors.toList());

        List<String> requestKeywords =
                keywords.stream()
                        .filter(keyword -> keyword != null)
                        .map(String::trim)
                        .filter(keyword -> !keyword.isBlank())
                        .map(keyword ->
                                keyword.toLowerCase(Locale.ROOT)
                        )
                        .distinct()
                        .collect(Collectors.toList());

        if (requestKeywords.isEmpty()) {
            return 0.0;
        }

        long matchCount = requestKeywords.stream()
                .filter(journalistKeywords::contains)
                .count();

        double keywordRatio =
                (double) matchCount / requestKeywords.size();

        return keywordRatio * 40.0;
    }
}