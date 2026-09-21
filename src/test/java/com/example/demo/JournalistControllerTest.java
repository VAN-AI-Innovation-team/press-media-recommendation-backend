package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controller.JournalistController;
import com.example.demo.domain.Journalist;
import com.example.demo.dto.JournalistResponseDto;
import com.example.demo.service.JournalistRecommendService;
import com.example.demo.service.JournalistService;

@WebMvcTest(JournalistController.class)
class JournalistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JournalistService journalistService;

    @MockBean
    private JournalistRecommendService recommendService;


    // 기자 정상 등록 → 201 Created
    @Test
    @DisplayName("기자 정상 등록 - 201 Created")
    void createJournalist_success() throws Exception {

        Journalist journalist = Journalist.builder()
                .name("박지훈")
                .email("park123@example.com")
                .build();

        JournalistResponseDto response =
                new JournalistResponseDto(journalist);

        given(journalistService.createJournalist(any()))
                .willReturn(response);

        String request = """
                {
                    "name": "박지훈",
                    "email": "park123@example.com",
                    "pressId": 1
                }
                """;

        mockMvc.perform(
                post("/api/journalists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
        )
        .andExpect(status().isCreated());
    }


    // 기자 이름 누락 → 400 Bad Request
    @Test
    @DisplayName("기자 이름 누락 - 400 Bad Request")
    void createJournalist_nameMissing() throws Exception {

        String request = """
                {
                    "email": "test@example.com",
                    "pressId": 1
                }
                """;

        mockMvc.perform(
                post("/api/journalists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
        )
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.name")
                .value("name은 필수값입니다."));
    }


    // 이메일 형식 오류 → 400 Bad Request
    @Test
    @DisplayName("이메일 형식 오류 - 400 Bad Request")
    void createJournalist_invalidEmail() throws Exception {

        String request = """
                {
                    "name": "박지훈",
                    "email": "wrong-email",
                    "pressId": 1
                }
                """;

        mockMvc.perform(
                post("/api/journalists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
        )
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.email")
                .value("올바른 이메일 형식이 아닙니다."));
    }

    @Test
@DisplayName("중복 이메일 → 409 Conflict")
void createJournalist_duplicateEmail() throws Exception {

    given(journalistService.createJournalist(any()))
            .willThrow(new com.example.demo.exception.DuplicateEmailException(
                    "이미 등록된 이메일입니다: park123@example.com"
            ));

    String request = """
            {
                "name": "박지훈",
                "email": "park123@example.com",
                "pressId": 1
            }
            """;

    mockMvc.perform(
            post("/api/journalists")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(request)
    )
    .andExpect(status().isConflict())
    .andExpect(jsonPath("$.email")
            .value("이미 등록된 이메일입니다: park123@example.com"));
}
@Test
@DisplayName("존재하지 않는 언론사 ID → 404 Not Found")
void createJournalist_invalidPressId() throws Exception {

    given(journalistService.createJournalist(any()))
            .willThrow(new IllegalArgumentException(
                    "존재하지 않는 언론사입니다. ID: 999"
            ));

    String request = """
            {
                "name": "박지훈",
                "email": "park123@example.com",
                "pressId": 999
            }
            """;

    mockMvc.perform(
            post("/api/journalists")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(request)
    )
    .andExpect(status().isNotFound())
    .andExpect(jsonPath("$.error")
            .value("존재하지 않는 언론사입니다. ID: 999"));
}

@Test
@DisplayName("pressId 누락 → 400 Bad Request")
void createJournalist_pressIdMissing() throws Exception {

    String request = """
            {
                "name": "박지훈",
                "email": "park123@example.com"
            }
            """;

    mockMvc.perform(
            post("/api/journalists")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(request)
    )
    .andExpect(status().isBadRequest())
    .andExpect(jsonPath("$.pressId")
            .value("pressId는 필수값입니다."));
}
}