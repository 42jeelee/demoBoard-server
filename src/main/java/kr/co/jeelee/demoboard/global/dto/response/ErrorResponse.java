package kr.co.jeelee.demoboard.global.dto.response;

import java.util.Map;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
	private final String code;
	private final Map<String, String> details;

	public static ErrorResponse of(String code, Map<String, String> details) {
		return new ErrorResponse(code, details);
	}
}
