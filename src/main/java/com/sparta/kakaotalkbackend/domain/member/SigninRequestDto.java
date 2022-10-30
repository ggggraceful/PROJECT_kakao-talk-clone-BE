package com.sparta.kakaotalkbackend.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SigninRequestDto {

	@NotBlank
	private String username;
	@NotBlank
	private String password;
}
