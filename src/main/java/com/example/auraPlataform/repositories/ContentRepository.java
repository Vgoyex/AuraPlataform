package com.example.auraPlataform.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auraPlataform.models.ContentModel;

@Repository
public interface ContentRepository extends JpaRepository<ContentModel, UUID> {
    List<ContentModel> findByContentIdUserPost(UUID contentIdUserPost);
    List<ContentModel> findContentByContentEmailUserPost(String email);
}
