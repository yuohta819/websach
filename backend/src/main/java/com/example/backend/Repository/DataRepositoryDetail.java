package com.example.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.backend.Model.Detail;

public interface DataRepositoryDetail extends JpaRepository<Detail,Long> {
    @Query("SELECT b FROM Detail b WHERE b.detailid = :id")
    List<Detail> findBook(@Param("id") Long id);
}
