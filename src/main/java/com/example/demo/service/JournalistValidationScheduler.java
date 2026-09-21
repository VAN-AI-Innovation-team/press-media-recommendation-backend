package com.example.demo.service;

import java.util.Hashtable;
import java.util.List;
import java.util.regex.Pattern;

import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Journalist;
import com.example.demo.domain.JournalistStatus;
import com.example.demo.domain.repository.JournalistRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JournalistValidationScheduler {

    private final JournalistRepository journalistRepository;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    @Scheduled(cron = "0 0 3 * * *") // 매일 새벽 3시 실행
    @Transactional
    public void validateJournalistContacts() {
        log.info("기자 연락처 데이터 정기 검증 스케줄러 시작");
        List<Journalist> journalists = journalistRepository.findAll();

        for (Journalist j : journalists) {
            if (j.getEmail() == null || !EMAIL_PATTERN.matcher(j.getEmail()).matches()) {
                j.updateStatus(JournalistStatus.INVALID_EMAIL);
                continue;
            }

            String domain = j.getEmail().substring(j.getEmail().indexOf("@") + 1);
            if (!hasMxRecord(domain)) {
                log.warn("유효하지 않은 이메일 도메인 감지: {}", domain);
                j.updateStatus(JournalistStatus.INVALID_EMAIL);
            }
        }
    }

    private boolean hasMxRecord(String domain) {
        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
            InitialDirContext ictx = new InitialDirContext(env);
            Attributes attrs = ictx.getAttributes(domain, new String[]{"MX"});
            Attribute attr = attrs.get("MX");
            return attr != null && attr.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}