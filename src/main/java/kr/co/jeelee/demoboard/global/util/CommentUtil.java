package kr.co.jeelee.demoboard.global.util;

import kr.co.jeelee.demoboard.domain.comment.dao.CommentRepository;
import kr.co.jeelee.demoboard.domain.comment.entity.Comment;
import kr.co.jeelee.demoboard.global.exception.custom.CustomException;
import kr.co.jeelee.demoboard.global.exception.custom.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CommentUtil implements EntityUtil<Comment> {

    private final CommentRepository commentRepository;

    @Override
    public Comment getById(UUID id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
    }

    @Override
    public boolean hasById(UUID id) {
        return commentRepository.existsById(id);
    }

    @Override
    public List<Comment> getAllByIds(List<UUID> ids) {
        List<Comment> comments = commentRepository.findAllById(ids);

        ids.stream()
                .filter(id -> comments.stream().noneMatch(comment -> comment.getId().equals(id)))
                .findFirst()
                .ifPresent(id -> {
                    throw new CustomException(ErrorCode.COMMENT_NOT_FOUND);
                });

        return commentRepository.findAllById(ids);
    }
}
