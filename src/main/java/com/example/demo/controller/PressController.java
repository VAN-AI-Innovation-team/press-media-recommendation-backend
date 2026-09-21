package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Press;
import com.example.demo.dto.PressRequestDto;
import com.example.demo.service.PressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/press")
@RequiredArgsConstructor
public class PressController {

    private final PressService pressService;

    // 언론사 등록 API
    @PostMapping
    public ResponseEntity<Press> createPress(@RequestBody PressRequestDto dto) {
        Press created = pressService.createPress(dto);
        return ResponseEntity.ok(created);
    }

    // 전체 언론사 목록 조회 API
    @GetMapping
    public ResponseEntity<List<Press>> getAllPresses() {
        List<Press> list = pressService.getAllPresses();
        return ResponseEntity.ok(list);
    }
}