package com.sparta.kakaotalkbackend.service;

import com.sparta.kakaotalkbackend.domain.ResponseDto;
import com.sparta.kakaotalkbackend.domain.member.Member;
import com.sparta.kakaotalkbackend.domain.member.MemberRequestDto;
import com.sparta.kakaotalkbackend.domain.member.MemberResponseDto;
import com.sparta.kakaotalkbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;


	//가입한 회원인지 아닌지 유효성 검사해주는 method
	public Member isPresentMember(String username) {
		Optional<Member> optionalMember = memberRepository.findByUsername(username);
		return optionalMember.orElse(null);
	}

	//회원가입
	@Transactional
	public ResponseDto<?> registerUser(MemberRequestDto memberRequestDto) {

		//중복처리
		if(null != isPresentMember(memberRequestDto.getUsername())){
			return ResponseDto.fail(409, "중복 아이디입니다", "Conflict");
		}

		//비밀번호 확인
		if(!memberRequestDto.getPassword().equals(memberRequestDto.getPasswordCheck())){
			return ResponseDto.fail(409, "비밀번호가 일치하지 않습니다", "Conflict");
		}

		Member member = Member.builder()
				.username(memberRequestDto.getUsername())
				.nickname(memberRequestDto.getNickname())
				.image(memberRequestDto.getImage())
				.password(passwordEncoder.encode(memberRequestDto.getPassword()))
				.build();
		memberRepository.save(member);
		return ResponseDto.success(
				MemberResponseDto.builder()
						.id(member.getId())
						.username(member.getUsername())
						.nickname(member.getNickname())
						.image(member.getImage())
						.build()
		);
	}
}
