package kr.co.jeelee.demoboard.global.exception.custom;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	POST_NOT_FOUND(HttpStatus.BAD_REQUEST, "POST-001", "해당 게시물을 찾을 수 없습니다."),
	POST_PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "POST-002", "비밀번호가 일치하지 않습니다."),
	;

	private final HttpStatus status;
	private final String code;
	private final String message;

}
