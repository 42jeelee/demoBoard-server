package kr.co.jeelee.demoboard.domain.category.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kr.co.jeelee.demoboard.domain.category.dao.CategoryRepository;
import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryResponse;
import kr.co.jeelee.demoboard.global.exception.custom.CustomException;
import kr.co.jeelee.demoboard.global.exception.custom.ErrorCode;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepository categoryRepository;

	@Override
	public List<CategoryResponse> findAll(final boolean isPostCount) {
		Map<Long, Long> countPostByCategoryId;

		if (isPostCount) {
			countPostByCategoryId = categoryRepository.countPostByCategoryId().stream()
				.collect(Collectors.toMap(
					result -> (Long) result[0],
					result -> (Long) result[1]
				));
		} else {
			countPostByCategoryId = null;
		}

		return categoryRepository.findAll().stream()
			.map(categoryEntity -> {
				Long postCount = isPostCount
					? countPostByCategoryId.get(categoryEntity.getId())
					: null;

				return CategoryResponse.of(categoryEntity, postCount);
			}).toList();
	}

	@Override
	public CategoryResponse findById(final Long id, final boolean isPostCount) {
		Map<Long, Long> countPostByCategoryId;

		if (isPostCount) {
			countPostByCategoryId = categoryRepository.countPostByCategoryId().stream()
				.collect(Collectors.toMap(
					result -> (Long) result[0],
					result -> (Long) result[1]
				));
		} else {
			countPostByCategoryId = null;
		}

		return categoryRepository.findById(id)
			.map(categoryEntity -> {
				final Long postCount = isPostCount
					? countPostByCategoryId.get(categoryEntity.getId())
					: null;

				return CategoryResponse.of(categoryEntity, postCount);
			})
			.orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
	}

}
