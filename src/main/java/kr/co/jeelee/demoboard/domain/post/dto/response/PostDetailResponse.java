package kr.co.jeelee.demoboard.domain.post.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryResponse;
import kr.co.jeelee.demoboard.domain.member.dto.response.MemberSimpleResponse;
import kr.co.jeelee.demoboard.domain.post.entity.Post;

public record PostDetailResponse(
		UUID id, String title, MemberSimpleResponse author,
		List<CategoryResponse> categories, String content, Long views,
		LocalDateTime updatedAt, LocalDateTime createdAt
) {
	public static PostDetailResponse from(Post post) {
		List<CategoryResponse> categories = post.getPostCategories().stream()
				.map(postCategory -> CategoryResponse.from(postCategory.getB()))
				.toList();

		return new PostDetailResponse(
				post.getId(),
				post.getTitle(),
				MemberSimpleResponse.from(post.getAuthor()),
				categories,
				post.getContent(),
				post.getViews(),
				post.getUpdatedAt(),
				post.getCreatedAt()
		);
	}
}
