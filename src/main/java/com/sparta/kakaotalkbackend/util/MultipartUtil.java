package com.sparta.kakaotalkbackend.util;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class MultipartUtil {

    private static final String BASE_DIR = "Pictures";

    /**
     * 로컬에서의 사용자 홈 디렉토리 경로를 반환합니다.
     */
    public static String getLocalHomeDir() {
        return System.getProperty("user.home");
    }

    /**
     * 새로운 파일 고유 ID를 생성합니다.
     * @return 36자리의 UUID
     */
    public static String createFileId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Multipart 의 ContentType 값에서 / 이후 확장자만 잘라냅니다.
     * @param contentType ex) image/png
     * @return ex) png
     */
    public static String getFormat(String contentType) {
        if (StringUtils.hasText(contentType)) {
            return contentType.substring(contentType.lastIndexOf('/') + 1);
        }
        return null;
    }

    // 이미지 path 설정
    public static String createPath(MultipartFile multipartFile) {
        // fileId -> 생성된 파일 고유 ID
        final String fileId = MultipartUtil.createFileId();
        // format -> 확장자
        final String format = MultipartUtil.getFormat(multipartFile.getContentType());

        return String.format("%s/%s.%s", BASE_DIR, fileId, format);
    }
}

