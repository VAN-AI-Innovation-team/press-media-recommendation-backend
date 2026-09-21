package com.example.demo.domain; // 본인 프로젝트 패키지 경로에 맞게 수정

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "press")
public class Press {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // 언론사 이름 (예: OO일보)

    private String category; // 주요 분야 (정치, IT, 경제 등)

    @Builder
    public Press(String name, String category) {
        this.name = name;
        this.category = category;
    }
}