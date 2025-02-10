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
    public CommentResponse findById(UUID parentId, UUID id) {
        return commentRepository.findByIdAndPost_Id(id, parentId)
                .map(CommentResponse::from)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
    }

    @Override
    @Transactional
    public CommentResponse createByParentId(UUID parentId, CommentCreateRequest request) {
        Post post = postUtil.getById(parentId);
        Member author = memberUtil.getById(request.authorId());

        Comment comment = Comment.of(author, request.content(), post);

        return CommentResponse.from(commentRepository.save(comment));
    }

    @Override
    @Transactional
    public void delete(UUID parentId, UUID id) {
        if (!commentRepository.isInPost(id, parentId)) {
            throw new CustomException(ErrorCode.POST_NOT_FOUND);
        }
        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentResponse> findAll(UUID parentId, Pageable pageable) {
        return commentRepository.findByPost_Id(parentId, pageable).stream()
                .map(CommentResponse::from)
                .toList();
    }

}
