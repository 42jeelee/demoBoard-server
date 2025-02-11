package kr.co.jeelee.demoboard.global.dao;

import kr.co.jeelee.demoboard.domain.relationship.postCategory.entity.PostCategory;
import kr.co.jeelee.demoboard.global.entity.BaseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RelationshipRepository<A extends BaseEntity, B extends BaseEntity> {

    boolean existsByA_IdAndB_Id(UUID aId, UUID bId);

    Optional<PostCategory> findByA_IdAndB_Id(UUID aId, UUID bId);

    List<A> findAsByB_id(UUID bId);

    List<B> findBsByA_id(UUID aId);

}
