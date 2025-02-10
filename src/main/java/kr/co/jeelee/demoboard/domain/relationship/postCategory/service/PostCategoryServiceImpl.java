package kr.co.jeelee.demoboard.domain.relationship.postCategory.service;

import kr.co.jeelee.demoboard.domain.relationship.postCategory.dao.PostCategoryRepository;
import kr.co.jeelee.demoboard.domain.relationship.postCategory.entity.PostCategory;
import kr.co.jeelee.demoboard.global.entity.RelationshipEntityId;
import kr.co.jeelee.demoboard.global.service.SimpleRelationshipService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class PostCategoryServiceImpl extends SimpleRelationshipService<PostCategoryRepository, PostCategory> {

    public PostCategoryServiceImpl(PostCategoryRepository repository) {
        super(repository);
    }

    @Override
    protected PostCategory createEntity(UUID aId, UUID bId) {
        RelationshipEntityId id = RelationshipEntityId.of(aId, bId);
        return PostCategory.of(id);
    }
}
