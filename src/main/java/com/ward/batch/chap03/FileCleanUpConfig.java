package com.ward.batch.chap03;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class FileCleanUpConfig {
	private final JobRepository jobRepository;
	private final PlatformTransactionManager platformTransactionManager;

	@Bean
	public Tasklet fileCleanUpTasklet() {
		return new FileCleanUpTasklet("./test-logs", 30);
	}

	@Bean
	public Job fileCleanUpJob() {
		return new JobBuilder("fileCleanUpJob", jobRepository)
			.start(fileCleanUpStep())
			.build();
	}

	@Bean
	public Step fileCleanUpStep() {
		return new StepBuilder("fileCleanUpStep", jobRepository)
			.tasklet(fileCleanUpTasklet(), platformTransactionManager)
			.build();
	}
}
