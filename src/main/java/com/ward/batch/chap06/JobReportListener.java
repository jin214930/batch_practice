package com.ward.batch.chap06;

import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobReportListener {
	private final EmailProvider emailProvider;

	@BeforeJob
	public void beforeJob(JobExecution jobExecution) {
		log.info("배치를 시작합니다. id: {}", jobExecution.getJobInstanceId());
	}

	@AfterJob
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus().isUnsuccessful()) {
			log.warn("배치를 실패했습니다. id: {}", jobExecution.getJobInstanceId());
			emailProvider.send(
				"wlsaud2149@naver.com",
				"배치 실패 알림",
				"job id: " + jobExecution.getJobInstanceId() + " 실패"
			);
		} else {
			log.info("배치를 성공했습니다.");
		}
	}
}
