package com.example.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Model.Detail;
import com.example.backend.Repository.DataRepositoryDetail;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/detail")

public class DetailController {
    @Value("${frontend_url}")
    private String frontendUrl; 
    @Autowired
    private DataRepositoryDetail connection;
    @GetMapping("/{id}")
    @CrossOrigin(origins = "${frontend_url}")
    public List<Detail> getID(@PathVariable Long id) {
        List<Detail> list = connection.findBook(id);
        return list;
    }


}
