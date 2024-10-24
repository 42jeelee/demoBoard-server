package kr.co.jeelee.demoboard.domain.post.dto.response;

import java.time.LocalDateTime;

import kr.co.jeelee.demoboard.domain.post.entity.PostEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PostSummaryResponse {
	private final Long id;
	private final String title;
	private final String author;
	private final LocalDateTime createAt;

	public static PostSummaryResponse of(PostEntity postEntity) {
		return new PostSummaryResponse(
			postEntity.getId(),
			postEntity.getTitle(),
			postEntity.getAuthor(),
			postEntity.getCreatedAt()
		);
	}
}
