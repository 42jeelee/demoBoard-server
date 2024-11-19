package kr.co.jeelee.demoboard.global.dto.response;

import java.time.LocalDateTime;

public record GlobalResponse<T>(String message, T data, LocalDateTime timestamp) {

	public static <T> GlobalResponse<T> ok(String message, T data) {
		return new GlobalResponse<>(message, data, LocalDateTime.now());
	}

	public static <T> GlobalResponse<T> error(String message, T data) {
		return new GlobalResponse<>(message, data, LocalDateTime.now());
	}

}
