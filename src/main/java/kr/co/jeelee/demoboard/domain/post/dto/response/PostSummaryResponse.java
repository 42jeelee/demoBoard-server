package kr.co.jeelee.demoboard.domain.post.dto.response;

import java.time.LocalDateTime;

import kr.co.jeelee.demoboard.domain.post.entity.PostEntity;

public record PostSummaryResponse(Long id, String title, String author, LocalDateTime createAt) {
	public static PostSummaryResponse of(PostEntity postEntity) {
		return new PostSummaryResponse(
				postEntity.getId(),
				postEntity.getTitle(),
				postEntity.getAuthor(),
				postEntity.getCreatedAt()
		);
	}
}
