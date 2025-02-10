package kr.co.jeelee.demoboard.domain.relationship.postCategory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kr.co.jeelee.demoboard.global.entity.RelationshipEntity;
import kr.co.jeelee.demoboard.global.entity.RelationshipEntityId;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_category")
@NoArgsConstructor
@Getter
public class PostCategory extends RelationshipEntity {

    private PostCategory(RelationshipEntityId id) {
        this.id = id;
    }

    public static PostCategory of(RelationshipEntityId id) {
        return new PostCategory(id);
    }
}
