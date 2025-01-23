package com.example.auraPlataform.dto;

import jakarta.validation.constraints.NotBlank;

public record UsersDto(@NotBlank String userName, 
@NotBlank String userNickName, 
@NotBlank String userEmail,
@NotBlank String userPassword){

}

// userNickName; // Nome de perfil
// userName; // Vai ser o @