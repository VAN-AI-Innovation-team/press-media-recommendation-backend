package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Journalist;
import com.example.demo.domain.Press;
import com.example.demo.domain.repository.JournalistRepository;
import com.example.demo.domain.repository.PressRepository;
import com.example.demo.dto.JournalistRequestDto;
import com.example.demo.dto.JournalistResponseDto;
import com.example.demo.exception.DuplicateEmailException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JournalistService {

    private final JournalistRepository journalistRepository;
    private final PressRepository pressRepository;

    @Transactional
    public JournalistResponseDto createJournalist(JournalistRequestDto requestDto) {

        // 이메일 중복 확인
        if (journalistRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateEmailException(
                    "이미 등록된 이메일입니다: " + requestDto.getEmail()
            );
        }

        // 존재하는 언론사인지 확인
        Press press = pressRepository.findById(requestDto.getPressId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "존재하지 않는 언론사입니다. ID: " + requestDto.getPressId()
                        )
                );

        Journalist journalist = Journalist.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .press(press)
                .build();

        return new JournalistResponseDto(
                journalistRepository.save(journalist)
        );
    }

    public List<JournalistResponseDto> getAllJournalists() {
        return journalistRepository.findAll().stream()
                .map(JournalistResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<JournalistResponseDto> getJournalistsByPress(Long pressId) {
        return journalistRepository.findByPressId(pressId).stream()
                .map(JournalistResponseDto::new)
                .collect(Collectors.toList());
    }
}