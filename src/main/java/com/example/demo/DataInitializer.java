package com.example.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Article;
import com.example.demo.domain.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ArticleRepository articleRepository;

    @Override
    public void run(String... args) {

        if (articleRepository.count() > 0) {
            return;
        }

        Article article1 = Article.builder()
                .category("금융·증시")
                .title("AI 기대와 금리 인상이 엇갈린 하루…코스피 상승폭 축소")
                .summary("엔비디아 실적이 국내 반도체주와 코스피를 끌어올렸지만, 한국은행의 기준금리 인상 발표 뒤 상승 폭은 줄었다.")

                .highlights(List.of(
                        "엔비디아 실적 기대가 국내 반도체주와 코스피 상승을 이끌었다.",
                        "한국은행의 기준금리 인상 발표 이후 지수 상승 폭은 축소됐다."
                ))

                .body(List.of(
                        "코스피는 엔비디아의 실적 발표 이후 국내 반도체 종목에 매수세가 유입되면서 상승 출발했다.",
                        "삼성전자와 SK하이닉스 등 주요 반도체 종목이 강세를 보이면서 지수는 장중 7,000선에 가까워졌다.",
                        "시장 분위기가 달라진 계기는 한국은행의 기준금리 발표였다.",
                        "수급에서는 외국인과 기관이 순매수에 나선 반면 개인 투자자는 매도 우위를 보였다.",
                        "이번 흐름은 글로벌 인공지능 투자 기대와 국내 통화정책 변화가 증시에 서로 다른 방향으로 작용할 수 있음을 보여준다."
                ))

                .publishedAt("2026-08-27")
                .publishedLabel("2026.08.27")

                .authorId("kim-yoo-hyang")
                .authorName("연합뉴스 · 김유향 기자")
                .authorNameEn("Yonhap News Agency · Kim Yoo-hyang")

                .imageSrc("/images/internal-review/01-kospi.jpg")
                .imageAlt("서울 중구 하나은행 본점 딜링룸 현황판에 표시된 코스피와 코스닥 종가")
                .imageCaption("서울 중구 하나은행 본점 딜링룸 현황판")
                .imageCredit("연합뉴스")
                .imageWidth(1200)
                .imageHeight(799)
                .imageObjectPosition("50% 50%")
                .imageInternalReviewOnly(true)
                .imageSourceUrl("https://www.yna.co.kr/view/AKR20260827142951008")

                .accent("#6f96b7")
                .visual("data")

                .categoryEn("Finance & Markets")
                .titleEn("AI optimism and rate hike pull the KOSPI in opposite directions")
                .summaryEn("Strong AI expectations lifted Korean chip stocks, while a rate increase reduced the market's gains.")

                .highlightsEn(List.of(
                        "AI optimism supported Korean semiconductor shares.",
                        "The market gave back part of its gains after the rate increase."
                ))

                .bodyEn(List.of(
                        "The KOSPI opened higher as buying interest flowed into Korean semiconductor stocks after Nvidia's earnings announcement.",
                        "Major semiconductor stocks, including Samsung Electronics and SK hynix, strengthened during the session.",
                        "Market sentiment shifted after the Bank of Korea announced its interest-rate decision.",
                        "Foreign and institutional investors were net buyers, while individual investors showed net selling.",
                        "The session showed how global AI expectations and domestic monetary policy can affect markets in different directions."
                ))

                .imageAltEn("KOSPI and KOSDAQ figures displayed at a dealing room in Seoul")
                .imageCaptionEn("Market indicators displayed at a dealing room in Seoul")
                .imageCreditEn("Yonhap News Agency")

                .build();


        Article article2 = Article.builder()
                .category("경제·거시경제")
                .title("반도체·소비 회복 반영…성장률 전망 3.3%로 상향")
                .summary("한국은행이 반도체 수출과 정보기술 설비투자 호조, 소비 회복을 반영해 올해 성장률 전망을 높였다.")

                .highlights(List.of(
                        "한국은행이 올해 경제성장률 전망치를 3.3%로 높였다.",
                        "반도체 수출과 IT 설비투자, 소비 회복이 전망 상향의 주요 배경으로 꼽혔다."
                ))

                .body(List.of(
                        "한국은행은 올해 경제성장률 전망치를 기존 2.6%에서 3.3%로 높였다.",
                        "전망 상향의 주요 배경으로는 반도체 수출의 강한 흐름이 제시됐다.",
                        "정보기술 분야의 설비투자도 성장 전망을 높인 요인이다.",
                        "소비 회복 역시 이번 전망에 포함됐다.",
                        "실제 성장 흐름은 앞으로의 수출 추세와 가계 소비, 물가, 금리 여건에 따라 달라질 수 있다."
                ))

                .publishedAt("2026-08-27")
                .publishedLabel("2026.08.27")

                .authorId("kim-young-bae")
                .authorName("한겨레 · 김영배 기자")
                .authorNameEn("The Hankyoreh · Kim Young-bae")

                .imageSrc("/images/internal-review/02-growth.webp")
                .imageAlt("클린룸 내부의 반도체 생산 라인")
                .imageCaption("반도체 생산 시설")
                .imageCredit("내부 검토용")
                .imageInternalReviewOnly(true)

                .accent("#668f91")
                .visual("climate")

                .categoryEn("Economy & Macroeconomics")
                .titleEn("Growth forecast raised to 3.3% on semiconductor and consumption recovery")
                .summaryEn("The Bank of Korea raised its growth forecast, reflecting stronger semiconductor exports, IT investment and consumption.")

                .highlightsEn(List.of(
                        "The Bank of Korea raised its economic growth forecast to 3.3%.",
                        "Semiconductor exports, IT investment and recovering consumption supported the revision."
                ))

                .bodyEn(List.of(
                        "The Bank of Korea raised its economic growth forecast for this year from 2.6% to 3.3%.",
                        "Strong semiconductor exports were cited as a major reason for the upward revision.",
                        "Investment in information technology also contributed to the stronger outlook.",
                        "A recovery in consumption was included in the revised projection.",
                        "Actual growth will depend on exports, household consumption, inflation and interest-rate conditions."
                ))

                .build();


        Article article3 = Article.builder()
                .category("의료·과학")
                .title("혈액 기반 대장암 선별 연구, 접근성과 검증 과제 함께 제시")
                .summary("국내 연구진이 혈액 속 세포유리DNA를 AI로 분석하는 대장암 선별 방법을 시험해 높은 진단 정확도를 확인했다.")

                .highlights(List.of(
                        "혈액 속 세포유리DNA를 AI로 분석하는 대장암 선별 방법이 연구됐다.",
                        "검사 접근성은 높일 수 있지만 추가적인 임상 검증이 필요하다."
                ))

                .body(List.of(
                        "국내 연구진이 혈액 속 세포유리 DNA를 인공지능으로 분석해 대장암 가능성을 선별하는 방법을 시험했다.",
                        "인공지능은 분석 과정에서 나타나는 패턴을 구분하고 대장암과 관련된 신호를 찾는 데 사용됐다.",
                        "혈액을 이용하는 방식은 대장내시경이나 대변 채취에 부담을 느끼는 사람에게 새로운 검사 선택지가 될 가능성이 있다.",
                        "이번 결과만으로 기존 검사를 곧바로 대체할 수 있다고 판단하기는 어렵다.",
                        "향후 연구에서는 정확도와 활용 가능성을 함께 확인하는 과정이 중요하다."
                ))

                .publishedAt("2026-08-27")
                .publishedLabel("2026.08.27")

                .authorId("kim-tae-hoon")
                .authorName("경향신문 · 김태훈 기자")
                .authorNameEn("Kyunghyang Shinmun · Kim Tae-hoon")

                .imageSrc("/images/internal-review/03-blood-screening.png")
                .imageAlt("정상 대장에서 용종과 대장암으로 진행되는 단계를 보여주는 자료 이미지")
                .imageCaption("대장암 진행 단계를 보여주는 자료 이미지")
                .imageCredit("내부 검토용")
                .imageInternalReviewOnly(true)

                .accent("#526f8c")
                .visual("culture")

                .categoryEn("Medicine & Science")
                .titleEn("Blood-based colorectal cancer screening study highlights accessibility and validation challenges")
                .summaryEn("Researchers tested an AI-based method that analyzes cell-free DNA in blood for colorectal cancer screening.")

                .highlightsEn(List.of(
                        "AI was used to analyze cell-free DNA in blood for colorectal cancer screening.",
                        "The method may improve accessibility but requires further clinical validation."
                ))

                .bodyEn(List.of(
                        "Researchers tested a method that analyzes cell-free DNA in blood using artificial intelligence to screen for colorectal cancer.",
                        "AI was used to identify patterns associated with colorectal cancer.",
                        "A blood-based method may provide an alternative for people who find colonoscopy or stool sampling burdensome.",
                        "The findings do not yet mean existing screening methods can be immediately replaced.",
                        "Further studies are needed to confirm accuracy and practical usefulness."
                ))

                .build();


        Article article4 = Article.builder()
                .category("사회·보건정책")
                .title("지역 중심 의료체계 논의…재정 배분 기준이 핵심")
                .summary("의료혁신위원회가 동네 의료기관과 보건소의 역할을 재편하고 지역별 건강 수요에 따른 재정 배분 방안을 제안했다.")

                .highlights(List.of(
                        "지역 중심 의료체계에서 동네 의료기관과 보건소의 역할 재편이 논의됐다.",
                        "지역별 인구와 건강 수요를 반영한 재정 배분 방식이 제안됐다."
                ))

                .body(List.of(
                        "의료혁신위원회는 지역 주민이 일상에서 먼저 만나는 동네 의료기관과 보건소의 역할을 새롭게 구성하는 방안을 논의했다.",
                        "동네 의료기관과 보건소는 주민과 가까운 곳에서 의료와 건강 서비스를 제공한다.",
                        "재정은 모든 지역에 같은 기준으로 배분하기보다 지역별 인구와 건강 수요를 고려해 지원하는 방안이 제안됐다.",
                        "각 기관의 책임과 협력 범위를 구체적으로 정해야 한다.",
                        "현장의 여건을 반영하면서 제도를 지속적으로 조정하는 과정이 중요하다."
                ))

                .publishedAt("2026-08-27")
                .publishedLabel("2026.08.27")

                .authorId("heo-yoon-hee")
                .authorName("한겨레 · 허윤희 기자")
                .authorNameEn("The Hankyoreh · Heo Yoon-hee")

                .imageSrc("/images/internal-review/04-primary-care.webp")
                .imageAlt("의료기관에서 의사가 환자와 상담하는 모습")
                .imageCaption("의료기관에서 진행되는 진료 상담")
                .imageCredit("내부 검토용")
                .imageInternalReviewOnly(true)

                .accent("#6b98ac")
                .visual("education")

                .categoryEn("Society & Health Policy")
                .titleEn("Regional healthcare system discussions focus on funding allocation")
                .summaryEn("A healthcare reform committee proposed reorganizing local medical services and allocating resources according to regional health needs.")

                .highlightsEn(List.of(
                        "The roles of local clinics and public health centers are being reconsidered.",
                        "Funding may be allocated according to regional population and health needs."
                ))

                .bodyEn(List.of(
                        "The healthcare reform committee discussed reorganizing the roles of local clinics and public health centers.",
                        "These institutions provide healthcare services close to local residents.",
                        "The committee proposed considering regional population and health needs when allocating financial support.",
                        "The responsibilities and cooperation between institutions need to be defined.",
                        "The system will need continued adjustment to reflect conditions in each region."
                ))

                .build();


        Article article5 = Article.builder()
                .category("AI·산업")
                .title("주행거리 너머 소프트웨어 경쟁…AI가 바꾸는 미래 모빌리티")
                .summary("미래 모빌리티 전시회를 통해 전기차 경쟁이 주행거리에서 소프트웨어와 자율 판단 능력 중심으로 이동하는 흐름을 살펴본다.")

                .highlights(List.of(
                        "전기차 경쟁의 중심이 주행거리에서 소프트웨어 역량으로 확대되고 있다.",
                        "AI 기반 자율 판단 능력이 미래 모빌리티의 핵심 경쟁 요소로 떠오르고 있다."
                ))

                .body(List.of(
                        "미래 모빌리티 전시에서는 전기차와 로보택시가 단순한 이동 수단을 넘어 소프트웨어 중심의 기기로 변화하는 흐름이 나타났다.",
                        "배터리 성능과 주행거리는 여전히 전기차 경쟁에서 중요한 요소다.",
                        "차량이 주변 도로 상황을 인식하고 스스로 판단하는 인공지능 역량이 새로운 경쟁 요소로 떠오르고 있다.",
                        "차량에서 생성되는 데이터와 소프트웨어가 안정적으로 연결돼야 한다.",
                        "앞으로의 모빌리티 경쟁은 소프트웨어 완성도와 자율 판단 능력을 중심으로 전개될 가능성이 있다."
                ))

                .publishedAt("2026-08-26")
                .publishedLabel("2026.08.26")

                .authorId("heo-baek-yoon")
                .authorName("서울신문 · 허백윤 기자")
                .authorNameEn("Seoul Shinmun · Heo Baek-yoon")

                .imageSrc("/images/internal-review/05-mobility.jpg")
                .imageAlt("2026 퓨처 모빌리티 위크 전시장에 전시된 첨단 개인용 항공기")
                .imageCaption("미래 모빌리티 전시장")
                .imageCredit("내부 검토용")
                .imageInternalReviewOnly(true)

                .accent("#6f8497")
                .visual("interview")

                .categoryEn("AI & Industry")
                .titleEn("Software joins driving range as AI reshapes future mobility")
                .summaryEn("Future mobility competition is shifting toward software and autonomous decision-making capabilities.")

                .highlightsEn(List.of(
                        "Competition in electric vehicles is expanding beyond driving range to software.",
                        "AI-powered autonomous decision-making is becoming a major competitive factor."
                ))

                .bodyEn(List.of(
                        "Future mobility exhibitions show electric vehicles and robotaxis becoming software-centered devices.",
                        "Battery performance and driving range remain important competitive factors.",
                        "AI systems capable of recognizing road conditions and making autonomous decisions are gaining importance.",
                        "Vehicle-generated data and software must be reliably connected.",
                        "Future mobility competition is likely to increasingly focus on software and autonomous decision-making."
                ))

                .build();


        Article article6 = Article.builder()
                .category("AI·정책")
                .title("사람 중심 AI 원칙 마련…현장 적용 기준이 다음 과제")
                .summary("정부가 인간 존엄과 공공선, 지속가능성을 중심으로 AI 개발자와 서비스 제공자, 이용자가 함께 따를 원칙을 마련했다.")

                .highlights(List.of(
                        "정부가 인간 존엄과 공공선을 중심으로 AI 윤리 원칙을 마련했다.",
                        "향후 과제는 원칙을 실제 개발과 서비스 현장에 적용할 구체적인 기준을 만드는 것이다."
                ))

                .body(List.of(
                        "정부가 마련한 대한민국 인공지능 윤리원칙은 인공지능의 개발과 이용 과정에서 지켜야 할 공통 가치를 제시한다.",
                        "원칙은 인간 존엄과 공공선, 지속가능성을 핵심 가치로 삼는다.",
                        "적용 대상은 인공지능을 만드는 개발자와 서비스를 제공하는 사업자에 한정되지 않는다.",
                        "책임은 기획, 개발, 제공, 이용 전 과정에 걸쳐 나뉜다.",
                        "앞으로는 각 주체가 원칙을 실제 업무와 이용 과정에 적용할 수 있는 방법을 마련하는 일이 중요하다."
                ))

                .publishedAt("2026-08-24")
                .publishedLabel("2026.08.24")

                .authorId("lee-tae-kwon")
                .authorName("SBS 뉴스 · 이태권 기자")
                .authorNameEn("SBS News · Lee Tae-kwon")

                .imageSrc("/images/internal-review/06-ai-ethics.jpg")
                .imageAlt("마이크를 들고 발언하는 배경훈 부총리")
                .imageCaption("AI 정책 관련 행사에서 발언하는 모습")
                .imageCredit("내부 검토용")
                .imageInternalReviewOnly(true)

                .accent("#6487ad")
                .visual("garden")

                .categoryEn("AI & Policy")
                .titleEn("Human-centered AI principles established as implementation becomes next challenge")
                .summaryEn("The government introduced AI principles centered on human dignity, public interest and sustainability.")

                .highlightsEn(List.of(
                        "The government introduced AI principles centered on human dignity and the public interest.",
                        "The next challenge is translating the principles into practical standards."
                ))

                .bodyEn(List.of(
                        "South Korea's AI ethics principles present common values for the development and use of artificial intelligence.",
                        "The principles emphasize human dignity, public interest and sustainability.",
                        "They apply not only to AI developers and service providers but also to users.",
                        "Responsibility is distributed across planning, development, provision and use.",
                        "The next step is developing practical methods for applying these principles in real-world settings."
                ))

                .build();


        articleRepository.saveAll(
                List.of(
                        article1,
                        article2,
                        article3,
                        article4,
                        article5,
                        article6
                )
        );

        System.out.println("Article sample data initialized.");
    }
}