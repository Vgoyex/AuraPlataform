// package com.example.auraPlataform.services;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;
// import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
// import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
// import software.amazon.awssdk.regions.Region;
// import software.amazon.awssdk.services.s3.S3Client;
// import software.amazon.awssdk.services.s3.model.PutObjectRequest;
// import software.amazon.awssdk.services.s3.model.S3Exception;

// import java.io.IOException;
// import java.net.URI;
// import java.util.UUID;

// @Service
// public class S3Service {

//     private final S3Client s3Client;

//     @Value("${aws.s3.bucketName}")
//     private String bucketName;

//     public S3Service(@Value("${aws.accessKeyId}") String accessKey,
//             @Value("${aws.secretAccessKey}") String secretKey,
//             @Value("${aws.s3.region}") String region) {
//         AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
//         this.s3Client = S3Client.builder()
//                 .credentialsProvider(StaticCredentialsProvider.create(credentials))
//                 .region(Region.of(region))
//                 .build();
//     }

//     public String uploadFile(MultipartFile file) throws IOException {
//         String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

//         PutObjectRequest putObjectRequest = PutObjectRequest.builder()
//                 .bucket(bucketName)
//                 .key(fileName)
//                 .contentType(file.getContentType())
//                 .build();

//         try {
//             s3Client.putObject(putObjectRequest,
//                     software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));
//             return "https://" + bucketName + ".s3." + s3Client.region() + ".amazonaws.com/" + fileName;
//         } catch (S3Exception e) {
//             throw new IOException("Erro ao enviar arquivo para S3: " + e.awsErrorDetails().errorMessage(), e);
//         }
//     }
// }
