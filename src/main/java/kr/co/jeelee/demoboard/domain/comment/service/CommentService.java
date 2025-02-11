package kr.co.jeelee.demoboard.domain.comment.service;

import kr.co.jeelee.demoboard.domain.comment.dto.request.CommentCreateRequest;
import kr.co.jeelee.demoboard.domain.comment.dto.response.CommentResponse;
import kr.co.jeelee.demoboard.global.service.ChildEntityService;
import kr.co.jeelee.demoboard.global.service.PageableChildEntityService;

import java.util.UUID;

public interface CommentService
        extends ChildEntityService<CommentCreateRequest, CommentResponse>,
        PageableChildEntityService<UUID, CommentResponse>
{}
