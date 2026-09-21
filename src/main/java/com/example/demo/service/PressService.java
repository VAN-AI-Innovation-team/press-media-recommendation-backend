package com.example.demo.service;

import com.example.demo.domain.Press;
import com.example.demo.domain.repository.PressRepository;
import com.example.demo.dto.PressRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PressService {

    private final PressRepository pressRepository;

    // 언론사 등록
    @Transactional
    public Press createPress(PressRequestDto dto) {
        Press press = Press.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .build();
        return pressRepository.save(press);
    }

    // 전체 언론사 조회
    public List<Press> getAllPresses() {
        return pressRepository.findAll();
    }
}