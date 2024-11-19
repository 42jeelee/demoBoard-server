package kr.co.jeelee.demoboard.global.dto.response;

import java.util.Map;

public record ErrorResponse(String code, Map<String, String> details) {
	public static ErrorResponse of(String code, Map<String, String> details) {
		return new ErrorResponse(code, details);
	}
}
