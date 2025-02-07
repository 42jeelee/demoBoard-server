package kr.co.jeelee.demoboard.domain.comment.entity;

import jakarta.persistence.*;
import kr.co.jeelee.demoboard.domain.member.entity.Member;
import kr.co.jeelee.demoboard.domain.post.entity.Post;
import kr.co.jeelee.demoboard.global.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@Getter
public class Comment extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private Member author;

	@Lob @Column(nullable = false)
	private String content;

	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	private Comment(Member author, String content, Post post) {
		this.author = author;
		this.content = content;
		this.post = post;
	}

	public static Comment of(Member author, String content, Post post) {
		return new Comment(author, content, post);
	}

}
