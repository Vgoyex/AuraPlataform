package com.example.auraPlataform.models;

import java.util.List;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="Tb_content")
public class ContentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //Poderia substituir AUTO por UUID
    //* Colunas no Banco(MySql nesse caso)
    private UUID idContent;
    private String contentName;
    private String contentDescription;
    private Integer contentLikes;
    private Integer contentQtdComments;
    private String contentComment;
    private List<String> contentCommentsList;
    private String contentFileName;
    private UUID contentIdUserPost;

    public String getContentEmailUserPost() {
        return contentEmailUserPost;
    }

    public void setContentEmailUserPost(String contentEmailUserPost) {
        this.contentEmailUserPost = contentEmailUserPost;
    }

    private String contentEmailUserPost;
    

    @Column(name="file_base64", columnDefinition = "TEXT")
    private String base64String;
    
    @Column(name = "content_file_bytes", columnDefinition = "BYTEA")
    private byte[] contentFileBytes; // tipo de dado que será convertido para base64
    

    
    public UUID getContentIdUserPost() {
        return contentIdUserPost;
    }

    public void setContentIdUserPost(UUID contentIdUserPost) {
        this.contentIdUserPost = contentIdUserPost;
    }

    public byte[] getContentFileBytes() {
        return contentFileBytes;
    }

    public String getContentFileName() {
        return contentFileName;
    }

    public void setContentFileName(String contentFileName) {
        this.contentFileName = contentFileName;
    }

    public void setContentFileBytes(byte[] contentFileBytes) {
        this.contentFileBytes = contentFileBytes;
    }

    public UUID getIdContent() {
        return idContent;
    }

    public void setIdContent(UUID idContent) {
        this.idContent = idContent;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public Integer getContentLikes() {
        return contentLikes;
    }

    public void setContentLikes(Integer contentLikes) {
        this.contentLikes = contentLikes;
    }

    public String getBase64String() {
        return base64String;
    }

    public void setBase64String(String base64String) {
        this.base64String = base64String;
    }

    public Integer getContentQtdComments() {
        return contentQtdComments;
    }

    public void setContentQtdComments(Integer contentQtdComments) {
        this.contentQtdComments = contentQtdComments;
    }

    public String getContentComment() {
        return contentComment;
    }

    public void setContentComment(String contentComment) {
        this.contentComment = contentComment;
    }

    public List<String> getContentCommentsList() {
        return contentCommentsList;
    }

    public void setContentCommentsList(List<String> contentCommentsList) {
        this.contentCommentsList = contentCommentsList;
    }

    public String getContentName(){
        return this.contentName;
    }

    public void setContentName(String contentName){
        this.contentName = contentName;
    }

}
