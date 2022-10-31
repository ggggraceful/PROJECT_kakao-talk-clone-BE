package com.sparta.kakaotalkbackend.controller;

import com.sparta.kakaotalkbackend.domain.ResponseDto;
import com.sparta.kakaotalkbackend.domain.member.MemberResponseDto;
import com.sparta.kakaotalkbackend.domain.member.SigninRequestDto;
import com.sparta.kakaotalkbackend.domain.member.MemberRequestDto;
import com.sparta.kakaotalkbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	//회원가입
	@PostMapping("/signup")
	public ResponseDto<MemberResponseDto> signup(@RequestPart(value = "dto") @Valid MemberRequestDto memberRequestDto,
												 @RequestPart(value = "file", required = false)MultipartFile multipartFile) {
		return memberService.registerUser(memberRequestDto, multipartFile);
	}

	//로그인
	@PostMapping("/signin")
	public ResponseDto<MemberResponseDto> signin(@RequestBody SigninRequestDto signinRequestDto, HttpServletResponse httpServletResponse){
		return memberService.signin(signinRequestDto, httpServletResponse);
	}

}
