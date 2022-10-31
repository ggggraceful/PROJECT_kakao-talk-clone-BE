package com.sparta.kakaotalkbackend.controller;

import com.sparta.kakaotalkbackend.domain.ResponseDto;
import com.sparta.kakaotalkbackend.domain.member.MemberResponseDto;
import com.sparta.kakaotalkbackend.domain.member.ProfileUpdateRequest;
import com.sparta.kakaotalkbackend.jwt.UserDetailsImpl;
import com.sparta.kakaotalkbackend.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myprofile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseDto<MemberResponseDto> getMyProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return profileService.getMyProfile(userDetails.getMember());
    }

    /*
    마이 프로필 수정
    기존 데이터를 프로필 수정할 때 보내서 바꾸지 않은 데이터는 기존 데이터가 리쿼스트로 오도록 하자.
    예를 들어 이미지만 바꾸고 상태메세지는 안바꾸는 경우......................
     */

    @PutMapping
    public ResponseDto<MemberResponseDto> updateMyProfile(@RequestPart ProfileUpdateRequest profileUpdateRequest,
                                                          @RequestPart(value = "file", required = false) MultipartFile multipartFile,
                                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return profileService.updateMyProfile(profileUpdateRequest, multipartFile, userDetails.getMember());
    }


}
