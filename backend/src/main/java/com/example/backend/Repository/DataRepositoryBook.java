package com.example.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.backend.Model.Account;
import com.example.backend.Model.Book;

import jakarta.transaction.Transactional;

public interface DataRepositoryBook extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.is_deleted = False ")
    List<Book> findAll();

    @Query("SELECT b FROM Book b WHERE b.category.categoryid = :id")
    List<Book> findCategory(@Param("id") Integer id);

    @Query("SELECT b FROM Book b WHERE b.bookid = :id")
    Book findEdit(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Book SET is_deleted = True WHERE bookid=:id")
    void getBookDelete(@Param("id") Integer id);
}