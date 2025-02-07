package kr.co.jeelee.demoboard.domain.post.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryResponse;
import kr.co.jeelee.demoboard.domain.member.dto.response.MemberSimpleResponse;
import kr.co.jeelee.demoboard.domain.post.entity.Post;

public record PostDetailResponse(
		UUID id, String title, MemberSimpleResponse author,
		CategoryResponse category, String content, Long views,
		LocalDateTime updatedAt, LocalDateTime createdAt
) {
	public static PostDetailResponse from(Post post) {
		return new PostDetailResponse(
				post.getId(),
				post.getTitle(),
				MemberSimpleResponse.from(post.getAuthor()),
				CategoryResponse.from(post.getCategory()),
				post.getContent(),
				post.getViews(),
				post.getUpdatedAt(),
				post.getCreatedAt()
		);
	}
}
