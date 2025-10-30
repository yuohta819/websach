# Sử dụng image chính thức của Java 17
FROM openjdk:17-jdk-slim

# Thư mục làm việc trong container
WORKDIR /app

# Copy toàn bộ project vào trong container
COPY . .

# Cấp quyền thực thi cho Maven Wrapper
RUN chmod +x mvnw

# Build ứng dụng (tạo file JAR)
RUN ./mvnw clean package -DskipTests

# Chạy ứng dụng từ file JAR
CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]
