package kr.co.jeelee.demoboard.domain.member.dao;

import kr.co.jeelee.demoboard.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {

}
