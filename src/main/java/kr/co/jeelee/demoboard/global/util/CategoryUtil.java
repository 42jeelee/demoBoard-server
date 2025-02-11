package kr.co.jeelee.demoboard.global.util;

import kr.co.jeelee.demoboard.domain.category.dao.CategoryRepository;
import kr.co.jeelee.demoboard.domain.category.entity.Category;
import kr.co.jeelee.demoboard.global.exception.custom.CustomException;
import kr.co.jeelee.demoboard.global.exception.custom.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CategoryUtil implements EntityUtil<Category> {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    @Override
    public boolean hasById(UUID id) {
        return categoryRepository.existsById(id);
    }

    @Override
    public List<Category> getAllByIds(List<UUID> ids) {
        List<Category> categories = categoryRepository.findAllById(ids);

        ids.stream()
                .filter(id -> categories.stream().noneMatch(category -> category.getId().equals(id)))
                .findFirst()
                .ifPresent(id -> {
                    throw new CustomException(ErrorCode.CATEGORY_NOT_FOUND);
                });

        return categoryRepository.findAllById(ids);
    }
}
