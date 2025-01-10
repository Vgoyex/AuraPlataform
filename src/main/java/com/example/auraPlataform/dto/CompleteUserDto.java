package com.example.auraPlataform.dto;

import jakarta.validation.constraints.NotBlank;

public record CompleteUserDto(
 @NotBlank String userName,
 @NotBlank String userNickName,
 @NotBlank String userEmail,
 @NotBlank String userPassword,
 @NotBlank String userCompleteName,
 @NotBlank String userBirthDate,
 @NotBlank String userPhoneNumber,
 @NotBlank String userCep,
 @NotBlank String userAdress,
 @NotBlank String userAdressNumber,
 String userAdressComplement,
 @NotBlank String userNeighborhood,
 @NotBlank String userCity,
 @NotBlank String userState
 ) {
}
