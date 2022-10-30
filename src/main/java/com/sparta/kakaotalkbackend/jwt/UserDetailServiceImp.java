package com.sparta.kakaotalkbackend.jwt;

import com.sparta.kakaotalkbackend.domain.member.Member;
import com.sparta.kakaotalkbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImp implements UserDetailsService {

	private final MemberRepository memberRepository;

	//시큐리티 session(내부 Authentication(내부 userDetails))
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("아이디를 찾을 수 없습니다." + username));
		return new UserDetailImp(member);
	}

}