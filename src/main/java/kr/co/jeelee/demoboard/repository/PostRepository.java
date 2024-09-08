package kr.co.jeelee.demoboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.jeelee.demoboard.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
