package kr.co.jeelee.demoboard.domain.post.dto.response;

import java.time.LocalDateTime;

import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryResponse;
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
	private final CategoryResponse category;
	private final String content;
	private final Long views;
	private final LocalDateTime updatedAt;
	private final LocalDateTime createdAt;

	public static PostDetailResponse of(PostEntity postEntity) {
		return new PostDetailResponse(
			postEntity.getId(),
			postEntity.getTitle(),
			postEntity.getAuthor(),
			CategoryResponse.of(postEntity.getCategory(), null),
			postEntity.getContent(),
			postEntity.getViews(),
			postEntity.getUpdatedAt(),
			postEntity.getCreatedAt()
		);
	}
}
