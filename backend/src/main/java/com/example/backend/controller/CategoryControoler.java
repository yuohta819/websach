package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Model.Category;
import com.example.backend.Repository.DataRepositoryCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/category")
public class CategoryControoler {
    @Autowired
    private DataRepositoryCategory category;
    @GetMapping("/call")
    public List<Category> getMethodName() {
        return category.findAll();
    }
    
}
