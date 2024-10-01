package kr.co.jeelee.demoboard.global.dto.response;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GlobalResponse<T> {
	private final String message;
	private final T data;
	private final LocalDateTime timestamp;

	public static <T> GlobalResponse<T> ok(String message, T data) {
		return new GlobalResponse<>(message, data, LocalDateTime.now());
	}

	public static <T> GlobalResponse<T> error(String message, T data) {
		return new GlobalResponse<>(message, data, LocalDateTime.now());
	}
}
