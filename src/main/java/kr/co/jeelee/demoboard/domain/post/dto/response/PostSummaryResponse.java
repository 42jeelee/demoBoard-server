package kr.co.jeelee.demoboard.domain.post.dto.response;

import java.time.LocalDateTime;

import kr.co.jeelee.demoboard.domain.post.entity.Post;

public record PostSummaryResponse(Long id, String title, String author, LocalDateTime createAt) {
	public static PostSummaryResponse of(Post post) {
		return new PostSummaryResponse(
				post.getId(),
				post.getTitle(),
				post.getAuthor(),
				post.getCreatedAt()
		);
	}
}
