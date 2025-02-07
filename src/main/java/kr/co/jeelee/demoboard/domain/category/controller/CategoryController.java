package kr.co.jeelee.demoboard.domain.category.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
		@PageableDefault(size = 5) Pageable pageable
	) {
		return categoryService.findAll(pageable);
	}

	@GetMapping("/{id}")
	public CategoryResponse getCategory(
		@PathVariable UUID id
	) {
		return categoryService.findById(id);
	}

}
