package com.example.auraPlataform.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.auraPlataform.repositories.UsersRepository;


@Controller
public class PagesController {

    @Autowired
    UsersRepository usersRepositoryM;

    @GetMapping("/aura")    
    public String getHome(){
        return "index";// "index" corresponde ao arquivo "index.html"
    }

    @GetMapping("/aura/create-content")
    public String getCreateContent(){
        return "create-content";
    }


}
