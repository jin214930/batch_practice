package com.ward.batch.chap03;

import java.io.File;
import java.time.LocalDate;

import org.jspecify.annotations.Nullable;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class FileCleanUpTasklet implements Tasklet {
	private final String root;
	private final int retentionDays;

	@Override
	public @Nullable RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		LocalDate cutoffDate = LocalDate.now().minusDays(retentionDays);
		File folder = new File(root);
		File[] files = folder.listFiles();

		if(files == null) {
			return RepeatStatus.FINISHED;
		}

		for (File file : files) {
			String name = file.getName();

			if (name.endsWith(".log") && name.length() >= 10) {
				String dateStr = name.substring(name.lastIndexOf("_") + 1, name.lastIndexOf("."));
				LocalDate fileDate = LocalDate.parse(dateStr);

				if(fileDate.isBefore(cutoffDate)) {
					file.delete();
					log.info("삭제된 로그 파일: {}", name);
				}
			}
		}

		return RepeatStatus.FINISHED;
	}
}
