package com.example.auraPlataform.dto;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;

public record ContentDto(String contentName,
@NotBlank String contentDescription,
Integer contentLikes,
Integer contentQtdComments,
String contentComment,
List<String> contentCommentsList,
MultipartFile contentFile,
@NotBlank UUID contentIdUserPost,
@NotBlank String contentEmailUserPost
) {

}
