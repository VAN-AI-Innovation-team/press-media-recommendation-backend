package com.example.demo;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.domain.Journalist;
import com.example.demo.domain.JournalistStatus;
import com.example.demo.domain.repository.JournalistRepository;
import com.example.demo.dto.JournalistRecommendResponseDto;
import com.example.demo.service.JournalistRecommendService;

@ExtendWith(MockitoExtension.class)
class JournalistRecommendServiceTest {

    @Mock
    private JournalistRepository journalistRepository;

    @InjectMocks
    private JournalistRecommendService recommendService;

    @Test
    @DisplayName("IT 카테고리 및 키워드 기반 기자 추천 테스트")
    void recommendTest() {
        // given
        Journalist j1 = Journalist.builder()
                .name("김테크")
                .email("tech@example.com")
                .department("IT")
                .recentArticleKeywords("AI, Cloud, Java")
                .build();

        given(journalistRepository.findByStatus(JournalistStatus.ACTIVE))
                .willReturn(List.of(j1));

        // when
        List<JournalistRecommendResponseDto> result = recommendService.recommendJournalists("IT", List.of("AI", "Java"));

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("김테크");
        assertThat(result.get(0).getScore()).isGreaterThan(0.0);
    }
}