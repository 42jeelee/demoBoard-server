package kr.co.jeelee.demoboard.domain.post.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import kr.co.jeelee.demoboard.domain.member.dto.response.MemberSimpleResponse;
import kr.co.jeelee.demoboard.domain.post.entity.Post;

public record PostSummaryResponse(
		UUID id, String title,
		MemberSimpleResponse author, LocalDateTime createAt
) {
	public static PostSummaryResponse from(Post post) {
		return new PostSummaryResponse(
				post.getId(),
				post.getTitle(),
				MemberSimpleResponse.from(post.getAuthor()),
				post.getCreatedAt()
		);
	}
}
