package kr.co.jeelee.demoboard.domain.comment.service;

import kr.co.jeelee.demoboard.domain.comment.dao.CommentRepository;
import kr.co.jeelee.demoboard.domain.comment.dto.request.CommentCreateRequest;
import kr.co.jeelee.demoboard.domain.comment.dto.response.CommentResponse;
import kr.co.jeelee.demoboard.domain.comment.entity.Comment;
import kr.co.jeelee.demoboard.domain.member.entity.Member;
import kr.co.jeelee.demoboard.domain.post.entity.Post;
import kr.co.jeelee.demoboard.global.exception.custom.CustomException;
import kr.co.jeelee.demoboard.global.exception.custom.ErrorCode;
import kr.co.jeelee.demoboard.global.util.MemberUtil;
import kr.co.jeelee.demoboard.global.util.PostUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final PostUtil postUtil;
    private final MemberUtil memberUtil;

    @Override
    public List<CommentResponse> findAllByPostId(UUID postId, Pageable pageable) {
        Post post = postUtil.getById(postId);

        return commentRepository.findByPost(post, pageable).stream()
                .map(CommentResponse::from)
                .toList();
    }

    @Override
    @Transactional
    public CommentResponse createByPostId(UUID postId, CommentCreateRequest request) {
        Post post = postUtil.getById(postId);
        Member member = memberUtil.getById(request.authorId());

        Comment comment = Comment.of(member, request.content(), post);
        return CommentResponse.from(commentRepository.save(comment));
    }

    @Override
    @Transactional
    public void deleteCommentById(UUID postId, UUID id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        if (!comment.getPost().getId().equals(postId)) {
            throw new CustomException(ErrorCode.COMMENT_NOT_FOUND);
        }

        commentRepository.delete(comment);
    }
}
