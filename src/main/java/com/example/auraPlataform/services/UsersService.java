package com.example.auraPlataform.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auraPlataform.models.UsersModel;
import com.example.auraPlataform.repositories.UsersRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public boolean getByEmailBoolean(String userEmail) {
        Optional<UsersModel> findUserEmail = usersRepository.findByUserEmail(userEmail);
        if(findUserEmail.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    public boolean getByUserNameBoolean(String userName){
        Optional<UsersModel> findUserName = usersRepository.findByUserName(userName);
        if(findUserName.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    public Optional<UsersModel> getById(UUID id){
        Optional<UsersModel> findUserId = usersRepository.findById(id);
        if(findUserId.isEmpty()){
            return null;
        }
        return findUserId;
    }

    public Optional<UsersModel> getByUserName(String userName){
        Optional<UsersModel> findUserName = usersRepository.findByUserName(userName);
        if(findUserName.isEmpty()){
            return null;
        }
        return findUserName;
    }

    public boolean validPassword(String password){
        // 7 chars + 1 numero + 1 char especial + 1 letra maiuscula
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+{}\\[\\]:;<>,.?/~`-])[A-Za-z\\d!@#$%^&*()_+{}\\[\\]:;<>,.?/~`-]{7,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        
        if(matcher.matches()){
            //! Deu erro
            return false;
        }else{
            //! Não deu erro
            return true;
        }
    }
}
