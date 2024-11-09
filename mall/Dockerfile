# Base image: Amazon Corretto 17
FROM amazoncorretto:17

# admin JAR 파일 복사
COPY build/libs/muve-mall.jar /app.jar

# 포트 설정
EXPOSE 8080

# prod 프로파일로 Spring Boot 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]