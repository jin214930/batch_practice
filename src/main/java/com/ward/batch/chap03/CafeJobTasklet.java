package com.ward.batch.chap03;

import org.jspecify.annotations.Nullable;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CafeJobTasklet implements Tasklet {
	private int cakeCount = 0;
	private static final int ORDER_TARGET = 5;

	@Override
	public @Nullable RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		cakeCount++;
		log.info("케이크를 만들고 있습니다. ({}/{})", cakeCount, ORDER_TARGET);

		if (cakeCount < ORDER_TARGET) {
			return RepeatStatus.CONTINUABLE;
		}

		log.info("주문하신 케이크 {}개 나왔습니다.", ORDER_TARGET);
		return RepeatStatus.FINISHED;
	}
}
