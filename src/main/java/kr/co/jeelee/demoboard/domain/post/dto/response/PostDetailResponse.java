package kr.co.jeelee.demoboard.domain.post.dto.response;

import java.time.LocalDateTime;

import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryResponse;
import kr.co.jeelee.demoboard.domain.post.entity.PostEntity;

public record PostDetailResponse(
		Long id, String title, String author,
		CategoryResponse category, String content, Long views,
		LocalDateTime updatedAt, LocalDateTime createdAt
) {
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
