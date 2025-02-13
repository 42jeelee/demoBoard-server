package kr.co.jeelee.demoboard.domain.auth.service;

import kr.co.jeelee.demoboard.domain.auth.dto.CustomUserDetails;
import kr.co.jeelee.demoboard.domain.member.dao.MemberRepository;
import kr.co.jeelee.demoboard.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return CustomUserDetails.from(member);
    }

}
