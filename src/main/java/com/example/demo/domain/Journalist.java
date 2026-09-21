package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "journalist")
public class Journalist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

    private String department; // 담당 분야 (예: IT, 정치, 경제)

    @Column(length = 1000)
    private String recentArticleKeywords; // 최근 기사 키워드 (쉼표 구분)

    @Enumerated(EnumType.STRING)
    private JournalistStatus status; // ACTIVE, INVALID_EMAIL, RETIRED, DUPLICATE

    private LocalDateTime lastValidatedAt; // 최근 데이터 검증 일시

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "press_id")
    private Press press;

    @Builder
    public Journalist(String name, String email, String department, String recentArticleKeywords, Press press) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.recentArticleKeywords = recentArticleKeywords;
        this.status = JournalistStatus.ACTIVE;
        this.press = press;
    }

    public void updateStatus(JournalistStatus status) {
        this.status = status;
        this.lastValidatedAt = LocalDateTime.now();
    }
}