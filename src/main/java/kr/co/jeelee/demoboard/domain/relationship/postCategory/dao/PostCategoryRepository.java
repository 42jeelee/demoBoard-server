package kr.co.jeelee.demoboard.domain.relationship.postCategory.dao;

import kr.co.jeelee.demoboard.domain.category.entity.Category;
import kr.co.jeelee.demoboard.domain.post.entity.Post;
import kr.co.jeelee.demoboard.domain.relationship.postCategory.entity.PostCategory;
import kr.co.jeelee.demoboard.global.dao.RelationshipRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostCategoryRepository
        extends JpaRepository<PostCategory, UUID>,
        RelationshipRepository<Post, Category>
{}
