package com.example.auraPlataform.controllers.content;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.auraPlataform.dto.ContentDto;
import com.example.auraPlataform.models.ContentModel;
import com.example.auraPlataform.repositories.ContentRepository;
import com.example.auraPlataform.services.ContentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/aura")
public class ContentController {

    /*
     * Content
    */

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    ContentService contentService;

    @GetMapping("/content")
    public ResponseEntity<List<ContentModel>> getAllContents() {
        return ResponseEntity.status(HttpStatus.OK).body(contentRepository.findAll());
    }

    @GetMapping("/contentHome")
    public ResponseEntity<Object> getContentHomePage(){
        return ResponseEntity.ok("");
    }

    @GetMapping("/content/{id}")
    public ResponseEntity<Object> getContent(@PathVariable(value = "id") UUID id) {
        Optional<ContentModel> contentObj = contentRepository.findById(id);
        if (contentObj.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Arquivo não encontrado.");
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf("video/mp4")).body(contentObj.get().getContentFileBytes());
    }


    @GetMapping("/get-content-front/{id}")
    public ResponseEntity<List<ContentModel>> getAllContentFrontById(@PathVariable(value = "id") UUID id) {
        List<ContentModel> files = contentRepository.findByContentIdUserPost(id);
        // Ao inves de fazer FIND ALL, fazer o get pelo id do creator
        // select * from tb_content where id_creator = id_creator_search
        return ResponseEntity.ok(files);
    }

    //! Rever isso aqui !
    @GetMapping("/get-content-front-e/{email}")
    public ResponseEntity<List<ContentModel>> getAllContentFrontByEmailUser(@PathVariable(value = "email") String email) {
        List<ContentModel> files = contentRepository.findContentByContentEmailUserPost(email);
        return ResponseEntity.ok(files);
    }


    // Deprecated
    @Deprecated
    @PostMapping("/content")
    public ResponseEntity<Object> postContent(@Valid ContentDto contentDto/**
                                                                           * , @RequestParam("contentFile")*
                                                                           * MultipartFile contentFile
                                                                           */
    ) {
        var contentObj = contentService.convertFormFileToByte(contentDto);
        // Setando o campo base64
        // contentObj.setBase64String(contentService.convertToBase64(contentObj.getContentFileBytes()));
        // if(contentObj.getContentFileBytes() != null){
        // }
        if (contentObj.getContentFileBytes() != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(contentRepository.save(contentObj));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(contentObj);
    }

    //! AQUI
    @PostMapping("/multi-content")
    public ResponseEntity<String> uploadFiles(@RequestParam("contentFile") List<MultipartFile> files, @Valid ContentDto contentDto) {
        List<ContentModel> contentFiles = contentService.uploadFiles(files);
        StringBuilder responseMessage = new StringBuilder();
        if (contentFiles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar arquivo.");
        } else {
            for (ContentModel file : contentFiles) {
                ContentModel contentFileToSave = new ContentModel();
                contentFileToSave.setBase64String(contentService.convertToBase64(file.getContentFileBytes()));
                contentFileToSave.setContentName(contentDto.contentName());
                contentFileToSave.setContentFileBytes(file.getContentFileBytes());
                contentFileToSave.setContentDescription(contentDto.contentDescription());
                contentFileToSave.setContentIdUserPost(contentDto.contentIdUserPost());
                contentFileToSave.setContentEmailUserPost(contentDto.contentEmailUserPost());
                contentRepository.save(contentFileToSave);
                responseMessage.append("Arquivo ").append(file.getContentFileName()).append(" salvo com sucesso!\n");
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("" + responseMessage);
    }

    // Put by Id
    @PutMapping("/content/{id}")
    public ResponseEntity<Object> updateuser(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid ContentDto contentDto) {
        Optional<ContentModel> contentObj = contentRepository.findById(id);
        if (contentObj.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Arquivo não encontrado.");
        }
        var userModel = contentObj.get();
        BeanUtils.copyProperties(contentDto, userModel);// Convertendo de DTO para Model
        return ResponseEntity.status(HttpStatus.OK).body(contentRepository.save(userModel));
    }

    // Delete by Id
    @DeleteMapping("/content/{id}")
    public ResponseEntity<Object> deleteuser(@PathVariable(value = "id") UUID id) {
        Optional<ContentModel> contentObj = contentRepository.findById(id);
        if (contentObj.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Arquivo não encontrado.");
        }
        contentRepository.delete(contentObj.get());
        return ResponseEntity.status(HttpStatus.OK).body("Arquivo excluído com sucesso.");
    }


    //! Testar pegar metadados do vídeo
    @GetMapping("/teste_service")
    public ResponseEntity<Object> testeTTT() {
        //teste passando o caminho do arquivo
        //tem que passar o arquivo no banco e fazer o service de outra maneira
        Object videoData = contentService.getVideoMetaData("\"C:\\Users\\Fernando\\Videos\\toma jack.mp4\"");
        if(videoData != null){
            String json = videoData.toString();
            // System.out.println("\n\n"+ json + "\n\n");
            return ResponseEntity.status(HttpStatus.OK).body(json);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro.");
        
    }
}
