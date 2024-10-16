package kr.co.jeelee.demoboard.domain.post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.co.jeelee.demoboard.global.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "POST")
@NoArgsConstructor
@Getter
public class PostEntity extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long postId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String author;

	private String password;

	@Column
	private String content;

	@Column(nullable = false)
	private Long views;

	private PostEntity(String title, String author, String password, String content) {
		this.title = title;
		this.author = author;
		this.password = password;
		this.content = content;
		this.views = 0L;
	}

	public static PostEntity create(String title, String author, String password, String content) {
		return new PostEntity(title, author, password, content);
	}

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
