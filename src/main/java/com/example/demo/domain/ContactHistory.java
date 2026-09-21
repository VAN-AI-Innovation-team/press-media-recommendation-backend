package com.example.demo.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContactHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "journalist_id")
    private Journalist journalist;

    private String pressReleaseTitle; // 발송한 보도자료 제목
    private LocalDateTime contactedAt; // 연락 시각
    private boolean isOpened; // 열람 여부

    @Builder
    public ContactHistory(Journalist journalist, String pressReleaseTitle) {
        this.journalist = journalist;
        this.pressReleaseTitle = pressReleaseTitle;
        this.contactedAt = LocalDateTime.now();
        this.isOpened = false;
    }
}