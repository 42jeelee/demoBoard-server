package kr.co.jeelee.demoboard.domain.post.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import kr.co.jeelee.demoboard.domain.post.event.FindPostEvent;
import kr.co.jeelee.demoboard.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;

@Component
@EnableAsync
@RequiredArgsConstructor
public class PostEventListener {
	private final PostService postService;

	@Async
	@EventListener
	public void onFindPost(FindPostEvent event) {
		postService.increaseViewsById(event.getPostId());
	}
}
