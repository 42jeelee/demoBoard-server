package kr.co.jeelee.demoboard.global.exception.custom;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER-001", "해당 유저를 찾을 수 없습니다."),

	POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST-001", "해당 게시물을 찾을 수 없습니다."),
	POST_PASSWORD_MISMATCH(HttpStatus.FORBIDDEN, "POST-002", "비밀번호가 일치하지 않습니다."),
	POST_ARGUMENTS_INVALID(HttpStatus.BAD_REQUEST, "POST-003", "필드의 값이 잘못되었습니다."),

	CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "CATEGORY-001", "해당 카테고리를 찾을 수 없습니다."),

	COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMENT-001", "해당 댓글을 찾을 수 없습니다."),

	REQUEST_INVALID(HttpStatus.BAD_REQUEST, "REQUEST-001", "잘못된 요청입니다."),
	ACCESS_DENIED(HttpStatus.FORBIDDEN, "REQUEST-002", "접근 권한이 없습니다."),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "REQUEST-003", "인증되지 않은 사용자는 접근할 수 없습니다."),
	INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "REQUEST-004", "유효하지 않은 토큰입니다."),
	TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "REQUEST-005", "이미 만료된 토큰입니다."),


	SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER-001", "서버에 예기치 않은 에러가 발생하였습니다."),
	KEYGEN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER-002", "서버가 키생성에 실패하였습니다."),
	;

	private final HttpStatus status;
	private final String code;
	private final String message;

}
