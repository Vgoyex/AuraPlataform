package com.example.auraPlataform.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.auraPlataform.dto.ContentDto;
import com.example.auraPlataform.models.ContentModel;
import com.example.auraPlataform.repositories.ContentRepository;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    public List<ContentModel> findContentByUserId(UUID contentIdUserPost) {
        List<ContentModel> findContentByUserIdList = contentRepository.findByContentIdUserPost(contentIdUserPost);
        return findContentByUserIdList;
    }

    public List<ContentModel> findContentByUserId(String email) {
        List<ContentModel> findContentByUserIdList = contentRepository.findContentByContentEmailUserPost(email);
        return findContentByUserIdList;
    }

    public String convertToBase64(byte[] obj){
        if(obj != null){
            try{
                String base64String = Base64.getEncoder().encodeToString(obj);
                // byte[] base64Byte = Base64.getDecoder().decode(base64String);
                return base64String;
            }catch(Exception error){
                throw new RuntimeException("Erro ao converter para base64");
            }
        }
        return null;
    }

    public String convertFromBase64(byte[] obj){
        String base64String = Base64.getEncoder().encodeToString(obj);
        if(base64String != null){
            return base64String;
        }
        return null;
    }

    public ContentModel convertFormFileToByte(ContentDto contentDto){
        var contentObj = new ContentModel();
        // Converte tipo de arquivo recebido pelo form para bytes
        try{
            byte[] contentFile = contentDto.contentFile().getInputStream().readAllBytes();
            contentObj.setContentFileBytes(contentFile);
            BeanUtils.copyProperties(contentDto, contentObj);// Convertendo de DTO para Model
        }catch(IOException error){
            throw new RuntimeException("Erro ao converter o arquivo ", error);
        }
        return contentObj;
    }

    public List<ContentModel> uploadFiles(/*@RequestParam("files") */List<MultipartFile> files){
        StringBuilder responseMessage = new StringBuilder();
        List<ContentModel> contentFiles = new ArrayList<ContentModel>();
        for (MultipartFile file : files) {
            try {
                ContentModel mediaFile = new ContentModel();
                mediaFile.setContentFileName(file.getOriginalFilename());
                
                if(mediaFile.getContentFileName() != null){
                    // Verifica se o nome do arquivo indica que é um vídeo
                    String fileName = mediaFile.getContentFileName();
                    String regex = ".*\\.(mp4|mov)$"; // Verifica extensões no final da string
                    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(fileName);
                    if (matcher.matches()) {
                        // É vídeo
                        // formatVideo(fileName);
                    } else {
                        // Não é vídeo
                        mediaFile.setContentFileBytes(file.getBytes());
                    }
                }
                
                mediaFile.setContentDescription("");
                contentFiles.add(mediaFile);
                responseMessage.append("Arquivo ").append(file.getOriginalFilename()).append(" salvo com sucesso!\n");

            } catch (IOException e) {
                responseMessage.append("Erro ao salvar o arquivo " + file.getOriginalFilename());
            }
        }
        if(contentFiles.isEmpty()){
            return null;
        }
        return contentFiles;    
    }

    public String formatVideo(String fileName){
         
        return "";
    }
    
    public String getVideoMetaData(String fileName){
        if(fileName == null){
            return "Empty file name!";
        }

        try{
            String tt = "cmd /c ffprobe " + fileName + " -show_streams -show_format -print_format json";
            Process process = Runtime.getRuntime().exec("cmd /c ffprobe " + fileName + " -show_streams -show_format -print_format json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String output = reader.readLine(); // Captura a saída do ffprobe
            
            process.waitFor();
        }catch(IOException | InterruptedException  err){
            err.printStackTrace();
        }
        
        return "";
    }
}
