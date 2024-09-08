package kr.co.jeelee.demoboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@NoArgsConstructor
@Getter @Setter
public class PostEntity extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long postId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String author;

	@Column
	private String content;

	public PostEntity(Long postId, String title, String author, String content) {
		this.postId = postId;
		this.title = title;
		this.author = author;
		this.content = content;
	}
}
