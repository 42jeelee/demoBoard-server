package kr.co.jeelee.demoboard.global.exception.custom;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST-001", "해당 게시물을 찾을 수 없습니다."),
	POST_PASSWORD_MISMATCH(HttpStatus.FORBIDDEN, "POST-002", "비밀번호가 일치하지 않습니다."),
	POST_ARGUMENTS_INVALID(HttpStatus.BAD_REQUEST, "POST-003", "필드의 값이 잘못되었습니다."),

	CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "CATEGORY-001", "해당 카테고리를 찾을 수 없습니다."),

	COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMENT-001", "해당 댓글을 찾을 수 없습니다."),

	;

	private final HttpStatus status;
	private final String code;
	private final String message;

}
