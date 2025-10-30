package com.example.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.backend.Model.BookChapter;

public interface DataRepositoryBookChapter extends JpaRepository<BookChapter, Long> {
    @Query("SELECT b FROM BookChapter b WHERE b.book.bookid = :id")
    List<BookChapter> getBook(@Param("id") Long id);
}
