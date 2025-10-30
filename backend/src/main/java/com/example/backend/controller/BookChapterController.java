package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Model.BookChapter;
import com.example.backend.Repository.DataRepositoryBookChapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/chapter")
public class BookChapterController {
    @Autowired
    private DataRepositoryBookChapter book;
    @GetMapping("/{id}")
    public List<BookChapter> getMethodName(@PathVariable("id") Long param) {
        return book.getBook(param);
    }
    
}
