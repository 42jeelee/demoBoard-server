package kr.co.jeelee.demoboard.domain.comment.dto.response;

import kr.co.jeelee.demoboard.domain.comment.entity.CommentEntity;

import java.time.LocalDateTime;

public record CommentResponse (Long id, String content, String author, LocalDateTime createdAt) {
    public static CommentResponse of(CommentEntity commentEntity) {
        return new CommentResponse(
                commentEntity.getId(),
                commentEntity.getContent(),
                commentEntity.getAuthor(),
                commentEntity.getCreatedAt()
        );
    }
}
