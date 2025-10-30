package com.example.backend.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Chapter")
public class BookChapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chapterid;

    private String chapter_title;
    private String chapter_content;

    // ✅ Quan hệ tới Book, Hibernate tự quản lý cột bookid
    @ManyToOne
    @JoinColumn(name = "bookid", referencedColumnName = "bookid")
    @JsonBackReference // 👈 Ngăn Jackson serialize ngược lại (Chapters -> Book)
    private Book book;

    public BookChapter() {}

    public BookChapter(Long chapterid, String chapter_title, String chapter_content) {
        this.chapterid = chapterid;
        this.chapter_title = chapter_title;
        this.chapter_content = chapter_content;
    }

    public Long getChapterid() { return chapterid; }
    public void setChapterid(Long chapterid) { this.chapterid = chapterid; }

    public String getChapter_title() { return chapter_title; }
    public void setChapter_title(String chapter_title) { this.chapter_title = chapter_title; }

    public String getChapter_content() { return chapter_content; }
    public void setChapter_content(String chapter_content) { this.chapter_content = chapter_content; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
}
