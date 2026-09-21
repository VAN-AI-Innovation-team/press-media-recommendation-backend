package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.JournalistRecommendResponseDto;
import com.example.demo.dto.JournalistRequestDto;
import com.example.demo.dto.JournalistResponseDto;
import com.example.demo.service.JournalistRecommendService;
import com.example.demo.service.JournalistService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/journalists")
@RequiredArgsConstructor
public class JournalistController {

    private final JournalistService journalistService;
    private final JournalistRecommendService recommendService;

    // 기자 등록 API
    @PostMapping
    public ResponseEntity<JournalistResponseDto> createJournalist(
            @Valid @RequestBody JournalistRequestDto requestDto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(journalistService.createJournalist(requestDto));
    }

    // 기자 목록 조회 API
    // pressId가 없으면 전체 기자 조회
    // pressId가 있으면 해당 언론사 기자 조회
    @GetMapping
    public ResponseEntity<List<JournalistResponseDto>> getJournalists(
            @RequestParam(required = false) Long pressId) {

        if (pressId != null) {
            return ResponseEntity.ok(
                    journalistService.getJournalistsByPress(pressId)
            );
        }

        return ResponseEntity.ok(
                journalistService.getAllJournalists()
        );
    }

    // 주제 및 키워드 기반 기자 자동 추천 API
    @GetMapping("/recommend")
    public ResponseEntity<List<JournalistRecommendResponseDto>> recommend(
            @RequestParam String category,
            @RequestParam List<String> keywords) {

        return ResponseEntity.ok(
                recommendService.recommendJournalists(category, keywords)
        );
    }
}