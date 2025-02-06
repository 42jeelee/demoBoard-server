package kr.co.jeelee.demoboard.domain.category.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryResponse;
import kr.co.jeelee.demoboard.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping
	public List<CategoryResponse> getCategories(
		@RequestParam(name = "postCount", defaultValue = "false") boolean isPostCount
	) {
		return categoryService.findAll(isPostCount);
	}

	@GetMapping("/{id}")
	public CategoryResponse getCategory(
		@PathVariable UUID id,
		@RequestParam(name = "postCount", defaultValue = "false") boolean isPostCount
	) {
		return categoryService.findById(id, isPostCount);
	}

}
