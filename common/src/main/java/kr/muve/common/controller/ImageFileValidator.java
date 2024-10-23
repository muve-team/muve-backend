package kr.muve.common.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Component
public class ImageFileValidator implements Validator {

    private static final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList("image/jpeg", "image/png", "image/gif");
    private static final long MAX_IMAGE_SIZE = 5 * 1024 * 1024; // 5MB

    @Override
    public boolean supports(Class<?> clazz) {
        return MultipartFile.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MultipartFile image = (MultipartFile) target;

        // 파일이 비어 있는지 확인
        if (image.isEmpty()) {
            errors.rejectValue("image", "image.empty", "파일이 없습니다.");
            return;
        }

        // 파일 크기 검증
        if (image.getSize() > MAX_IMAGE_SIZE) {
            errors.rejectValue("image", "image.size", "파일 크기는 5MB를 초과할 수 없습니다.");
        }

        // 파일 형식 검증
        if (!ALLOWED_CONTENT_TYPES.contains(image.getContentType())) {
            errors.rejectValue("image", "image.type", "허용되지 않는 파일 형식입니다.");
        }
    }
}
