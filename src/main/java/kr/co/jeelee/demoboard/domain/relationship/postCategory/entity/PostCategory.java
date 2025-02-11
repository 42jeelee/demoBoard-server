package kr.co.jeelee.demoboard.domain.relationship.postCategory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kr.co.jeelee.demoboard.domain.category.entity.Category;
import kr.co.jeelee.demoboard.domain.post.entity.Post;
import kr.co.jeelee.demoboard.global.entity.RelationshipEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_category")
@NoArgsConstructor
@Getter
public class PostCategory extends RelationshipEntity<Post, Category> {

    private PostCategory(Post post, Category category) {
        this.a = post;
        this.b = category;
    }

    public static PostCategory of(Post post, Category category) {
        return new PostCategory(post, category);
    }
}
