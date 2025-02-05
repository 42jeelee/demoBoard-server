package kr.co.jeelee.demoboard.domain.category.service;

import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kr.co.jeelee.demoboard.domain.category.dao.CategoryRepository;
import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryResponse;
import kr.co.jeelee.demoboard.domain.post.service.PostService;
import kr.co.jeelee.demoboard.global.exception.custom.CustomException;
import kr.co.jeelee.demoboard.global.exception.custom.ErrorCode;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	private final PostService postService;

	@Override
	public List<CategoryResponse> findAll(final boolean isPostCount) {
		Map<Long, Long> countPostByCategoryId = isPostCount
			? categoryRepository.countPostByCategoryId().stream()
				.collect(Collectors.toMap(
					result -> (Long) result[0],
					result -> (Long) result[1]
				))
			: Collections.emptyMap();

		return categoryRepository.findAll().stream()
			.map(categoryEntity -> {
				Long postCount = countPostByCategoryId.get(categoryEntity.getId());

				return CategoryResponse.from(categoryEntity, postCount);
			}).toList();
	}

	@Override
	public CategoryResponse findById(final Long id, final boolean isPostCount) {

		return categoryRepository.findById(id)
			.map(categoryEntity -> {
				final Long postCount = isPostCount
				? postService.countPostByCategoryId(id) : null;

				return CategoryResponse.from(categoryEntity, postCount);
			})
			.orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
	}

}
