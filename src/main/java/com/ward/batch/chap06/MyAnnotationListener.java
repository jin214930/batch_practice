package com.ward.batch.chap06;

import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyAnnotationListener {
	@BeforeJob
	public void announceStart(JobExecution jobExecution) {
		log.info("[job 시작] id: {}", jobExecution.getJobInstanceId());
	}

	@AfterJob
	public void announceEnd(JobExecution jobExecution) {
		if (jobExecution.getStatus().isUnsuccessful()) {
			log.warn("[job 실패] 관리자에게 문의하세요.");
		} else {
			log.info("[job 성공] id: {}", jobExecution.getJobInstanceId());
		}
	}
}
