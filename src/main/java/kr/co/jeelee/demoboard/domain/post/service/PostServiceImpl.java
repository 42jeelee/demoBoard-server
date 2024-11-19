package kr.co.jeelee.demoboard.domain.post.service;

import java.util.List;

import kr.co.jeelee.demoboard.domain.category.dao.CategoryRepository;
import kr.co.jeelee.demoboard.domain.category.entity.CategoryEntity;
import kr.co.jeelee.demoboard.domain.post.dto.request.PostCreateRequest;
import kr.co.jeelee.demoboard.domain.post.dto.request.PostUpdateRequest;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.jeelee.demoboard.domain.post.dto.response.PostDetailResponse;
import kr.co.jeelee.demoboard.domain.post.dto.response.PostSummaryResponse;
import kr.co.jeelee.demoboard.domain.post.entity.PostEntity;
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

	private final CategoryRepository categoryRepository;

	private final PasswordEncoder passwordEncoder;
	private final ApplicationEventPublisher eventPublisher;

	private boolean checkPassword(String password, String dbPassword) {
		return passwordEncoder.matches(password, dbPassword);
	}

	@Override
	public List<PostSummaryResponse> findAll(Pageable pageable) {
		return postRepository.findAll(pageable)
			.stream()
			.map(PostSummaryResponse::of)
			.toList();
	}

	@Override
	public PostDetailResponse findById(Long id) {

		eventPublisher.publishEvent(new FindPostEvent(id));

		return postRepository.findById(id)
			.map(PostDetailResponse::of)
			.orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
	}

	@Override
	@Transactional
	public PostDetailResponse create(PostCreateRequest request) {
		String encodedPassword = passwordEncoder.encode(request.password());
		CategoryEntity categoryEntity = categoryRepository.findById(request.categoryId())
				.orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));

		PostEntity postEntity = PostEntity.of(request.title(), request.author(), encodedPassword, categoryEntity, request.content());
		return PostDetailResponse.of(postRepository.save(postEntity));
	}

	@Override
	@Transactional
	public PostDetailResponse updateById(Long postId, PostUpdateRequest request) {
		PostEntity postEntity = postRepository.findById(postId)
				.orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

		if (!checkPassword(request.password(), postEntity.getPassword())) {
			throw new CustomException(ErrorCode.POST_PASSWORD_MISMATCH);
		}
		postEntity.update(request.title(), request.content());
		return PostDetailResponse.of(postRepository.save(postEntity));
	}

	@Override
	@Transactional
	public void deleteById(Long postId, String password) {
		postRepository.findPasswordById(postId)
				.ifPresentOrElse(
						dbPassword -> {
							if (!checkPassword(password, dbPassword)) {
								throw new CustomException(ErrorCode.POST_PASSWORD_MISMATCH);
							}
							postRepository.deleteById(postId);
						},
						() -> { throw new CustomException((ErrorCode.POST_NOT_FOUND)); }
				);
	}

	@Override
	@Transactional
	public void increaseViewsById(Long postId) {
		postRepository.incrementViews(postId);
	}
}
