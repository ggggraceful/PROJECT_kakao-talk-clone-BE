package com.sparta.kakaotalkbackend.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {

	@NotBlank
	@Pattern(regexp = "[a-z0-9]{4,12}", message = "아이디양식을 확인해주세요!")
//	@Pattern(regexp = "[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", message = "아이디양식을 확인해주세요!") // 이메일 형식
	private String username;

	@NotBlank
	private String nickname;

	@NotBlank
	private String image;

	@NotBlank
	@Pattern(regexp = "[a-z0-9]{4,32}", message = "비밀번호양식을 확인해주세요!")
	private String password;

	@NotBlank
	private String passwordCheck;

}
