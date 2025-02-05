package kr.co.jeelee.demoboard.domain.comment.service;

import kr.co.jeelee.demoboard.domain.comment.dao.CommentRepository;
import kr.co.jeelee.demoboard.domain.comment.dto.request.CommentCreateRequest;
import kr.co.jeelee.demoboard.domain.comment.dto.response.CommentResponse;
import kr.co.jeelee.demoboard.domain.comment.entity.Comment;
import kr.co.jeelee.demoboard.domain.post.dao.PostRepository;
import kr.co.jeelee.demoboard.domain.post.entity.Post;
import kr.co.jeelee.demoboard.global.exception.custom.CustomException;
import kr.co.jeelee.demoboard.global.exception.custom.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public List<CommentResponse> findAllByPostId(Long postId, Pageable pageable) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        return commentRepository.findByPost(post, pageable).stream()
                .map(CommentResponse::from)
                .toList();
    }

    @Override
    @Transactional
    public CommentResponse createByPostId(Long postId, CommentCreateRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        Comment comment = Comment.of(request.author(), request.content(), post);
        return CommentResponse.from(commentRepository.save(comment));
    }

    @Override
    @Transactional
    public void deleteCommentById(Long postId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        if (!comment.getPost().getId().equals(postId)) {
            throw new CustomException(ErrorCode.COMMENT_NOT_FOUND);
        }

        commentRepository.delete(comment);
    }
}
