package kr.co.jeelee.demoboard.domain.post.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindPostEvent {
	private final Long postId;

}
