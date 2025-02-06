package kr.co.jeelee.demoboard.domain.post.service;

import java.util.List;
import java.util.UUID;

import kr.co.jeelee.demoboard.domain.category.entity.Category;
import kr.co.jeelee.demoboard.domain.member.entity.Member;
import kr.co.jeelee.demoboard.domain.post.dto.request.PostCreateRequest;
import kr.co.jeelee.demoboard.domain.post.dto.request.PostUpdateRequest;

import kr.co.jeelee.demoboard.global.util.CategoryUtil;
import kr.co.jeelee.demoboard.global.util.MemberUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.jeelee.demoboard.domain.post.dto.response.PostDetailResponse;
import kr.co.jeelee.demoboard.domain.post.dto.response.PostSummaryResponse;
import kr.co.jeelee.demoboard.domain.post.entity.Post;
import kr.co.jeelee.demoboard.domain.post.dao.PostRepository;
import kr.co.jeelee.demoboard.domain.post.event.FindPostEvent;
import kr.co.jeelee.demoboard.global.exception.custom.CustomException;
import kr.co.jeelee.demoboard.global.exception.custom.ErrorCode;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;

	private final MemberUtil memberUtil;
	private final CategoryUtil categoryUtil;

	private final ApplicationEventPublisher eventPublisher;

	@Override
	public List<PostSummaryResponse> findAll(Pageable pageable) {
		return postRepository.findAll(pageable)
			.stream()
			.map(PostSummaryResponse::from)
			.toList();
	}

	@Override
	public PostDetailResponse findById(UUID id) {

		eventPublisher.publishEvent(new FindPostEvent(id));

		return postRepository.findById(id)
			.map(PostDetailResponse::from)
			.orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
	}

	@Override
	@Transactional
	public PostDetailResponse create(PostCreateRequest request) {
		Category category = categoryUtil.getById(request.categoryId());
		Member author = memberUtil.getById(request.authorId());

		Post post = Post.of(request.title(), author, category, request.content());
		return PostDetailResponse.from(postRepository.save(post));
	}

	@Override
	@Transactional
	public PostDetailResponse updateById(UUID id, PostUpdateRequest request) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

		post.update(request.title(), request.content());
		return PostDetailResponse.from(postRepository.save(post));
	}

	@Override
	@Transactional
	public void deleteById(UUID id) {
		postRepository.findById(id)
				.ifPresentOrElse(
                        postRepository::delete,
						() -> { throw new CustomException((ErrorCode.POST_NOT_FOUND)); }
				);
	}

	@Override
	@Transactional
	public void increaseViewsById(UUID postId) {
		postRepository.incrementViews(postId);
	}
}
