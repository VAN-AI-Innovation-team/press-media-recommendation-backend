package com.example.demo.domain;

import java.util.List;

import com.example.demo.converter.StringListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "articles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String title;

    @Column(length = 3000)
    private String summary;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> highlights;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> body;

    private String publishedAt;

    private String publishedLabel;

    // Author
    private String authorId;

    private String authorName;

    private String authorNameEn;

    // Image
    private String imageSrc;

    @Column(length = 2000)
    private String imageAlt;

    @Column(length = 2000)
    private String imageCaption;

    @Column(length = 2000)
    private String imageCredit;

    private Integer imageWidth;

    private Integer imageHeight;

    private String imageObjectPosition;

    private String imageCardFit;

    private Boolean imageInternalReviewOnly;

    @Column(length = 2000)
    private String imageSourceUrl;

    private String accent;

    private String visual;

    // English translation
    private String categoryEn;

    @Column(length = 2000)
    private String titleEn;

    @Column(length = 3000)
    private String summaryEn;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> highlightsEn;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> bodyEn;

    @Column(length = 2000)
    private String imageAltEn;

    @Column(length = 2000)
    private String imageCaptionEn;

    @Column(length = 2000)
    private String imageCreditEn;
}