package kr.co.jeelee.demoboard.domain.category.controller;

import java.util.List;

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
	public List<CategoryResponse> getCategories() {
		return categoryService.findAll();
	}

	@GetMapping("/{categoryId}")
	public CategoryResponse getCategory(@PathVariable Long categoryId) {
		return categoryService.findById(categoryId);
	}

}
