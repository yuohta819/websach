package com.example.backend.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.Model.Category;

public interface DataRepositoryCategory extends JpaRepository<Category, Long> {
    
}
