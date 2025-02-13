package kr.co.jeelee.demoboard.domain.member.dao;

import kr.co.jeelee.demoboard.domain.member.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {

    List<Member> searchMembersByNicknameOrName(String nickname, String name, Pageable pageable);

    Optional<Member> findByEmail(String email);

}
