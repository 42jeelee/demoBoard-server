package kr.co.jeelee.demoboard.domain.category.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import kr.co.jeelee.demoboard.domain.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    List<Category> searchCategoriesByName(String name, Pageable pageable);

}
