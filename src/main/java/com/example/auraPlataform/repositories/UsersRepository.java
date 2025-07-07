package com.example.auraPlataform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.auraPlataform.models.UsersModel;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel, UUID> {

    Optional<UsersModel> findByUserEmail(String userEmail);
    Optional<UsersModel> findByUserName(String userName);
    //! Verificar isso aqui
    Optional<UsersModel> findById(UUID id);
}
