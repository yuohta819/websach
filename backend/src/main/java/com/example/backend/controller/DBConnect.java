package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import com.example.backend.Model.Book;
import com.example.backend.Model.Detail;
import com.example.backend.Repository.DataRepositoryBook;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class DBConnect {
    @Value("${frontend_url}")
    private String frontendUrl; 
    @Autowired
    private DataRepositoryBook connection;
    @GetMapping("/all")
    @CrossOrigin(origins = "${frontend_url}")
    public List<Book> getAll() {
        List<Book> list = connection.findAll();
        return list;
    }
    
}
