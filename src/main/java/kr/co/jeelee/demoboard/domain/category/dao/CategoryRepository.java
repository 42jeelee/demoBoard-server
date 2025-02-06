package kr.co.jeelee.demoboard.domain.category.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import kr.co.jeelee.demoboard.domain.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {}
