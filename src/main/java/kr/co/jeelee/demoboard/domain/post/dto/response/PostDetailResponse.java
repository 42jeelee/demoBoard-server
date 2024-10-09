package kr.co.jeelee.demoboard.domain.post.dto.response;

import java.time.LocalDateTime;

import kr.co.jeelee.demoboard.domain.post.entity.PostEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDetailResponse {
	private final Long id;
	private final String title;
	private final String author;
	private final String content;
	private final Long views;
	private final LocalDateTime updatedAt;
	private final LocalDateTime createdAt;

	public static PostDetailResponse of(PostEntity postEntity) {
		return new PostDetailResponse(
			postEntity.getPostId(),
			postEntity.getTitle(),
			postEntity.getAuthor(),
			postEntity.getContent(),
			postEntity.getViews(),
			postEntity.getUpdatedAt(),
			postEntity.getCreatedAt()
		);
	}
}
