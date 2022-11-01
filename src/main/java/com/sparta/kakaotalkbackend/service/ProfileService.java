package com.sparta.kakaotalkbackend.service;

import com.sparta.kakaotalkbackend.domain.ResponseDto;
import com.sparta.kakaotalkbackend.domain.member.Member;
import com.sparta.kakaotalkbackend.domain.member.MemberResponseDto;
import com.sparta.kakaotalkbackend.domain.member.ProfileUpdateRequest;
import com.sparta.kakaotalkbackend.util.AmazonS3ResourceStorage;
import com.sparta.kakaotalkbackend.util.MultipartUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final AmazonS3ResourceStorage amazonS3ResourceStorage;

    /*
    마이 프로필 조회
     */
    public ResponseDto<MemberResponseDto> getMyProfile(Member member) {
//        check.memberExist(member);
        return ResponseDto.success(new MemberResponseDto(member));
    }

    /*
    마이 프로필 수정
     */
    @Transactional
    public ResponseDto<MemberResponseDto> updateMyProfile(ProfileUpdateRequest profileUpdateRequest,
                                                          MultipartFile multipartFile,
                                                          Member member) {
//        check.memberExist(member);

        String image = MultipartUtil.createPath(multipartFile);
        amazonS3ResourceStorage.store(image, multipartFile);

        member.update(profileUpdateRequest);
        return ResponseDto.success(new MemberResponseDto(member));
    }
}
