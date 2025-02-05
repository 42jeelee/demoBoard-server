package kr.co.jeelee.demoboard.domain.post.dto.response;

import java.time.LocalDateTime;

import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryResponse;
import kr.co.jeelee.demoboard.domain.post.entity.Post;

public record PostDetailResponse(
		Long id, String title, String author,
		CategoryResponse category, String content, Long views,
		LocalDateTime updatedAt, LocalDateTime createdAt
) {
	public static PostDetailResponse from(Post post) {
		return new PostDetailResponse(
				post.getId(),
				post.getTitle(),
				post.getAuthor(),
				CategoryResponse.from(post.getCategory()),
				post.getContent(),
				post.getViews(),
				post.getUpdatedAt(),
				post.getCreatedAt()
		);
	}
}
