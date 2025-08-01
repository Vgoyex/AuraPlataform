package com.example.auraPlataform.controllers.users;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.auraPlataform.dto.CompleteUserDto;
import com.example.auraPlataform.models.UsersModel;
import com.example.auraPlataform.repositories.UsersRepository;
import com.example.auraPlataform.services.UsersService;

import jakarta.validation.Valid;
import java.util.Optional;



@RestController
@RequestMapping("api/v1/aura")
public class UsersController {

    /*
     * Users  
    */
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UsersService usersService;

    @GetMapping("/users")
    public ResponseEntity<List<UsersModel>> getAllUsers(){
        System.out.println("\n Usuário Controller \n");
        return ResponseEntity.status(HttpStatus.OK).body(usersRepository.findAll());
    }

    // Get By Id
    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUser(@PathVariable(value="id") UUID id){
        System.out.println("\n Usuário Controller \n");
        Optional<UsersModel> userObj = usersService.getById(id);
        if(userObj == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID do usuário incorreto.");
        }
        if(userObj.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userObj.get());
    }

    // Será importante para pesquisa na barra de pesquisas
    @GetMapping("/users/profile/{userName}")
    public ResponseEntity<Object> getUserByName(@PathVariable(value="userName") String userName){
        System.out.println("\n Usuário Controller \n");
        Optional<UsersModel> userObj = usersService.getByUserName(userName);
        if(userObj == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userObj.get());
    }

    @PostMapping("/users")
    public ResponseEntity<Object> postUser(@RequestBody @Valid CompleteUserDto usersDto){
        //! Fazer lógica para salvar a senha
        System.out.println("\n Usuário Controller \n");
        var usersModel = new UsersModel();
        BeanUtils.copyProperties(usersDto, usersModel);// Convertendo de DTO para Model
        boolean errorMail = usersService.getByEmailBoolean(usersModel.getUserEmail());
        boolean errorUserName = usersService.getByUserNameBoolean(usersModel.getUserName());
        boolean errorPassword = usersService.validPassword(usersModel.getUserPassword());
        var bodyReturn = new HashMap<>();

        if(errorMail || errorUserName || errorPassword){
            bodyReturn.put("errorMail", errorMail);
            bodyReturn.put("errorUserName", errorUserName);
            bodyReturn.put("errorPassword", errorPassword);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyReturn);
        }else{
            bodyReturn.put("success", "Usuário criado com Sucesso!");
        }

        ResponseEntity.status(HttpStatus.CREATED).body(usersRepository.save(usersModel));
        // return ResponseEntity.status(HttpStatus.OK).body(usersModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com Sucesso!");
    }

    //! CASO SEJA VIA FORM
    // @PostMapping("/users")
    // public ResponseEntity<UsersModel> postHome(@Valid UsersDto usersDto){
    //     //! Fazer lógica para salvar a senha
    //     var usersModel = new UsersModel();
    //     BeanUtils.copyProperties(usersDto, usersModel);// Convertendo de DTO para Model
    //     ResponseEntity.status(HttpStatus.CREATED).body(usersRepository.save(usersModel));
    //     return ResponseEntity.status(HttpStatus.OK).body(usersModel);
    // }

    // Put by Id
    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUserById(@PathVariable(value="id") UUID id,
    @RequestBody @Valid CompleteUserDto userDto){
        System.out.println("\n Usuário Controller \n");
        Optional<UsersModel> userObj = usersRepository.findById(id);
        if(userObj.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
        }
        var userModel = userObj.get();
        BeanUtils.copyProperties(userDto, userModel);// Convertendo de DTO para Model
        return ResponseEntity.status(HttpStatus.OK).body(usersRepository.save(userModel));
    }

    @PutMapping("/users-u/{userName}")
    public ResponseEntity<Object> updateUserByUserName(@PathVariable(value="userName") String userName,
    @RequestBody @Valid CompleteUserDto userDto){
        System.out.println("\n Usuário Controller \n");
        Optional<UsersModel> userObj = usersRepository.findByUserName(userName);
        if(userObj.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
        }
        var userModel = userObj.get();
        BeanUtils.copyProperties(userDto, userModel);// Convertendo de DTO para Model
        return ResponseEntity.status(HttpStatus.OK).body(usersRepository.save(userModel));
    }


    // Delete by Id
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value="id") UUID id){
        System.out.println("\n Usuário Controller \n");
        Optional<UsersModel> userObj = usersRepository.findById(id);
        if(userObj.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
        }
        usersRepository.delete(userObj.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário excluído com sucesso");
    }

    // Delete by UserName
    @DeleteMapping("/users/{userName}")
    public ResponseEntity<Object> deleteUserByUserName(@PathVariable(value="userName") String userName){
        System.out.println("\n Usuário Controller \n");
        Optional<UsersModel> userObj = usersRepository.findByUserName(userName);
        if(userObj.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
        }
        usersRepository.delete(userObj.get());

        return ResponseEntity.status(HttpStatus.OK).body("Usuário excluído com sucesso");
    }


}
