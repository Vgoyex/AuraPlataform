package com.example.auraPlataform.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name="Tb_Users")
public class UsersModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //Poderia substituir AUTO por UUID

    //* Colunas no Banco(MySql nesse caso)
    private UUID idUser;
    private String userNickName; // Nome de perfil
    private String userName; // Vai ser o @
    private String userEmail;
    private String userPassword;

    private String userCompleteName;
    private String userBirthDate;
    private String userPhoneNumber;
    private String userCep;
    private String userAdress;
    private String userAdressNumber;
    private String userAdressComplement;
    private String userNeighborhood;
    private String userCity;
    private String userState;

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserCompleteName() {
        return userCompleteName;
    }

    public void setUserCompleteName(String userCompleteName) {
        this.userCompleteName = userCompleteName;
    }

    public String getUserBirthDate() {
        return userBirthDate;
    }

    public void setUserBirthDate(String userBirthDate) {
        this.userBirthDate = userBirthDate;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserCep() {
        return userCep;
    }

    public void setUserCep(String userCep) {
        this.userCep = userCep;
    }

    public String getUserAdress() {
        return userAdress;
    }

    public void setUserAdress(String userAdress) {
        this.userAdress = userAdress;
    }

    public String getUserAdressNumber() {
        return userAdressNumber;
    }

    public void setUserAdressNumber(String userAdressNumber) {
        this.userAdressNumber = userAdressNumber;
    }

    public String getUserAdressComplement() {
        return userAdressComplement;
    }

    public void setUserAdressComplement(String userAdressComplement) {
        this.userAdressComplement = userAdressComplement;
    }

    public String getUserNeighborhood() {
        return userNeighborhood;
    }

    public void setUserNeighborhood(String userNeighborhood) {
        this.userNeighborhood = userNeighborhood;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }


    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
