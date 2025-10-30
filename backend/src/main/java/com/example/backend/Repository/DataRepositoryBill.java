package com.example.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.Model.Bill;

public interface DataRepositoryBill extends JpaRepository<Bill,Long> {
    
}
