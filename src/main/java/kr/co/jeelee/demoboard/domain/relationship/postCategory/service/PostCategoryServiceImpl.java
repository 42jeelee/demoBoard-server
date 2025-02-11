package kr.co.jeelee.demoboard.domain.relationship.postCategory.service;

import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryDetailResponse;
import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryResponse;
import kr.co.jeelee.demoboard.domain.category.entity.Category;
import kr.co.jeelee.demoboard.domain.post.dto.response.PostDetailResponse;
import kr.co.jeelee.demoboard.domain.post.dto.response.PostSummaryResponse;
import kr.co.jeelee.demoboard.domain.post.entity.Post;
import kr.co.jeelee.demoboard.domain.relationship.postCategory.dao.PostCategoryRepository;
import kr.co.jeelee.demoboard.domain.relationship.postCategory.entity.PostCategory;
import kr.co.jeelee.demoboard.global.service.RelationshipEntityService;
import kr.co.jeelee.demoboard.global.util.CategoryUtil;
import kr.co.jeelee.demoboard.global.util.PostUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostCategoryServiceImpl
        implements RelationshipEntityService<
            PostDetailResponse, CategoryDetailResponse,
            PostSummaryResponse, CategoryResponse
        > {

    private final PostCategoryRepository postCategoryRepository;

    private final PostUtil postUtil;
    private final CategoryUtil categoryUtil;

    @Override
    public Boolean existById(UUID aId, UUID bId) {
        return postCategoryRepository.existsByA_IdAndB_Id(aId, bId);
    }

    @Override
    @Transactional
    public PostDetailResponse create(UUID aId, UUID bId) {
        Post post = postUtil.getById(aId);
        Category category = categoryUtil.getById(bId);
        PostCategory postCategory = PostCategory.of(post, category);

        category.getPostCategories().add(postCategory);
        post.getPostCategories().add(postCategoryRepository.save(postCategory));
        return PostDetailResponse.from(post);
    }

    @Override
    @Transactional
    public PostDetailResponse create(UUID aId, List<UUID> bIds) {
        Post post = postUtil.getById(aId);
        List<PostCategory> postCategories = categoryUtil.getAllByIds(bIds).stream()
                .map(category -> {
                    PostCategory postCategory = PostCategory.of(post, category);

                    category.getPostCategories().add(postCategory);
                    return postCategory;
                })
                .toList();

        post.getPostCategories().addAll(postCategoryRepository.saveAll(postCategories));
        return PostDetailResponse.from(post);
    }

    @Override
    @Transactional
    public CategoryDetailResponse create(List<UUID> aIds, UUID bId) {
        Category category = categoryUtil.getById(bId);
        List<PostCategory> postCategories = postUtil.getAllByIds(aIds).stream()
                .map(post -> {
                    PostCategory postCategory = PostCategory.of(post, category);

                    post.getPostCategories().add(postCategory);
                    return postCategory;
                })
                .toList();

        category.getPostCategories().addAll(postCategoryRepository.saveAll(postCategories));
        return CategoryDetailResponse.from(category);
    }

    @Override
    @Transactional
    public void deleteById(UUID aId, UUID bId) {
        postCategoryRepository.findByA_IdAndB_Id(aId, bId)
                .ifPresent(postCategoryRepository::delete);
    }

    @Override
    public List<PostSummaryResponse> getAsByBId(UUID bId) {
        return postCategoryRepository.findAsByB_id(bId).stream()
                .map(PostSummaryResponse::from)
                .toList();
    }

    @Override
    public List<CategoryResponse> getBsByAId(UUID aId) {
        return postCategoryRepository.findBsByA_id(aId).stream()
                .map(CategoryResponse::from)
                .toList();
    }

}
