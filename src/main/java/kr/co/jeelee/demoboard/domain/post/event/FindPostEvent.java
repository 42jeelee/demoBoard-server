package kr.co.jeelee.demoboard.domain.post.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class FindPostEvent {
	private final UUID postId;

}
