package kr.co.jeelee.demoboard.global.util;

import kr.co.jeelee.demoboard.domain.member.dao.MemberRepository;
import kr.co.jeelee.demoboard.domain.member.entity.Member;
import kr.co.jeelee.demoboard.global.exception.custom.CustomException;
import kr.co.jeelee.demoboard.global.exception.custom.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MemberUtil implements EntityUtil<Member> {

    private final MemberRepository memberRepository;

    @Override
    public Member getById(UUID id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
    }

    @Override
    public boolean hasById(UUID id) {
        return memberRepository.existsById(id);
    }

    @Override
    public List<Member> getAllByIds(List<UUID> ids) {
        List<Member> members = memberRepository.findAllById(ids);

        ids.stream()
                .filter(id -> members.stream().noneMatch(member -> member.getId().equals(id)))
                .findFirst()
                .ifPresent(id -> {
                    throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
                });

        return memberRepository.findAllById(ids);
    }

}
