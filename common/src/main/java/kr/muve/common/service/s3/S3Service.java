package kr.muve.common.service.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import kr.muve.common.exception.S3OperationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3 client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // 파일 업로드 메소드
    public String uploadFile(MultipartFile file) {
        try {
            String fileName = createFileName(file.getOriginalFilename()); // 고유한 파일 이름 생성
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            // 파일을 S3에 업로드
            client.putObject(bucket, fileName, file.getInputStream(), metadata);
            return client.getUrl(bucket, fileName).toString(); // 업로드된 파일의 URL 반환
        } catch (IOException e) {
            throw new S3OperationException(e.getMessage());
        }
    }

    // 파일 다운로드 메소드
    public S3Object downloadFile(String fileName) {
        return client.getObject(bucket, fileName); // S3에서 파일 다운로드
    }

    // 파일 삭제 메소드
    public void deleteFile(String fileName) {
        client.deleteObject(bucket, fileName); // S3에서 파일 삭제
    }

    // 고유한 파일 이름을 생성하는 메소드
    private String createFileName(String originalFileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }

    // 파일 확장자를 추출하는 메소드
    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("잘못된 파일 형식입니다: " + fileName);
        }
    }
}

