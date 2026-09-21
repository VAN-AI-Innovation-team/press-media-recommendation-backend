package com.example.demo.dto;

import java.util.List;

import com.example.demo.domain.Article;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArticleResponseDto {

    private Long id;

    private String category;

    private String title;

    private String summary;

    private List<String> highlights;

    private List<String> body;

    private String publishedAt;

    private String publishedLabel;

    private AuthorDto author;

    private ImageDto image;

    private String accent;

    private String visual;

    @Getter
    @Builder
    public static class AuthorDto {

        private String id;

        private String name;

        private String nameEn;
    }

    @Getter
    @Builder
    public static class ImageDto {

        private String src;

        private String alt;

        private String caption;

        private String credit;

        private Integer width;

        private Integer height;

        private String objectPosition;

        private String cardFit;

        private Boolean internalReviewOnly;

        private String sourceUrl;
    }

    public static ArticleResponseDto from(
            Article article,
            String language
    ) {

        boolean english =
                "en".equalsIgnoreCase(language);

        String category =
                english && article.getCategoryEn() != null
                        ? article.getCategoryEn()
                        : article.getCategory();

        String title =
                english && article.getTitleEn() != null
                        ? article.getTitleEn()
                        : article.getTitle();

        String summary =
                english && article.getSummaryEn() != null
                        ? article.getSummaryEn()
                        : article.getSummary();

        List<String> highlights =
                english && article.getHighlightsEn() != null
                        ? article.getHighlightsEn()
                        : article.getHighlights();

        List<String> body =
                english && article.getBodyEn() != null
                        ? article.getBodyEn()
                        : article.getBody();

        AuthorDto author =
                AuthorDto.builder()
                        .id(article.getAuthorId())
                        .name(
                                english &&
                                article.getAuthorNameEn() != null
                                        ? article.getAuthorNameEn()
                                        : article.getAuthorName()
                        )
                        .nameEn(article.getAuthorNameEn())
                        .build();

        ImageDto image =
                ImageDto.builder()
                        .src(article.getImageSrc())
                        .alt(
                                english &&
                                article.getImageAltEn() != null
                                        ? article.getImageAltEn()
                                        : article.getImageAlt()
                        )
                        .caption(
                                english &&
                                article.getImageCaptionEn() != null
                                        ? article.getImageCaptionEn()
                                        : article.getImageCaption()
                        )
                        .credit(
                                english &&
                                article.getImageCreditEn() != null
                                        ? article.getImageCreditEn()
                                        : article.getImageCredit()
                        )
                        .width(article.getImageWidth())
                        .height(article.getImageHeight())
                        .objectPosition(
                                article.getImageObjectPosition()
                        )
                        .cardFit(article.getImageCardFit())
                        .internalReviewOnly(
                                article.getImageInternalReviewOnly()
                        )
                        .sourceUrl(article.getImageSourceUrl())
                        .build();

        return ArticleResponseDto.builder()
                .id(article.getId())
                .category(category)
                .title(title)
                .summary(summary)
                .highlights(highlights)
                .body(body)
                .publishedAt(article.getPublishedAt())
                .publishedLabel(article.getPublishedLabel())
                .author(author)
                .image(image)
                .accent(article.getAccent())
                .visual(article.getVisual())
                .build();
    }
}