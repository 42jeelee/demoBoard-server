package kr.co.jeelee.demoboard.domain.post.service;

import java.util.List;

import kr.co.jeelee.demoboard.domain.category.dao.CategoryRepository;
import kr.co.jeelee.demoboard.domain.category.entity.Category;
import kr.co.jeelee.demoboard.domain.post.dto.request.PostCreateRequest;
import kr.co.jeelee.demoboard.domain.post.dto.request.PostUpdateRequest;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
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
			.map(PostDetailResponse::from)
			.orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
	}

	@Override
	@Transactional
	public PostDetailResponse create(PostCreateRequest request) {
		String encodedPassword = passwordEncoder.encode(request.password());
		Category category = categoryRepository.findById(request.categoryId())
				.orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));

		Post post = Post.of(request.title(), request.author(), encodedPassword, category, request.content());
		return PostDetailResponse.from(postRepository.save(post));
	}

	@Override
	@Transactional
	public PostDetailResponse updateById(Long postId, PostUpdateRequest request) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

		if (!checkPassword(request.password(), post.getPassword())) {
			throw new CustomException(ErrorCode.POST_PASSWORD_MISMATCH);
		}
		post.update(request.title(), request.content());
		return PostDetailResponse.from(postRepository.save(post));
	}

	@Override
	public Long countPostByCategoryId(Long categoryId) {
		return postRepository.countPostByCategoryId(categoryId);
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
