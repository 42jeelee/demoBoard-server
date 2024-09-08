package kr.co.jeelee.demoboard.request;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostRequest {

	@NotNull
	private final String title;

	@NotNull
	private final String author;

	private final String content;
}
