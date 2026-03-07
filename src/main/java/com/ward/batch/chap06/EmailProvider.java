package com.ward.batch.chap06;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmailProvider {
	public void send(String to, String subject, String message) {
		// 실제로는 메일 발송 로직
		log.info("[메일 발송 성공] 받는 사람: {}", to);
		log.info("제목: {}", subject);
		log.info("내용: {}", message);
	}
}
