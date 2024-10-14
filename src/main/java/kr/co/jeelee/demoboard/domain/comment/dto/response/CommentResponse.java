package kr.co.jeelee.demoboard.domain.comment.dto.response;

import kr.co.jeelee.demoboard.domain.comment.entity.CommentEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponse {
    private final Long id;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;

    public static CommentResponse of(CommentEntity commentEntity) {
        return new CommentResponse(
                commentEntity.getCommentId(),
                commentEntity.getContent(),
                commentEntity.getAuthor(),
                commentEntity.getCreatedAt()
        );
    }
}
