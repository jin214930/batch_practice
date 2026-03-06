package com.ward.batch.chap03;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LogGenerator {
	private static final String ROOT = "./test-logs";
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static void main(String[] args) throws IOException {
		File dir = new File(ROOT);

		if (!dir.exists()) {
			dir.mkdir();
		}

		createLogFile(dir, "access", 2);
		createLogFile(dir, "access", 0);
		createLogFile(dir, "service", 50);
		createLogFile(dir, "service", 100);
		createLogFile(dir, "system_config.conf", -1);

		System.out.println("테스트용 로그 파일 생성 완료! 경로: " + ROOT);
	}

	private static void createLogFile(File dir, String prefix, int daysAgo) throws IOException {
		String filename;

		if (daysAgo == -1) {
			filename = prefix;
		} else {
			LocalDate targetDate = LocalDate.now().minusDays(daysAgo);
			String dateStr = targetDate.format(DATE_TIME_FORMATTER);
			filename = prefix + "_" + dateStr + ".log";
		}

		File file = new File(dir, filename);

		if(file.createNewFile()) {
			System.out.println("파일 생성됨: " + filename);
		}
	}
}
