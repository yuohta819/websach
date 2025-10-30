package com.example.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Detail")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailid;

    private String describe;
    private String publisher;
    private String publishing;

    @Column(name = "numberofpage")
    private Integer numberofpage;

    private String isbn;
    private String format;

    // ✅ Sửa lại cho đúng quan hệ 1-1, không phải ManyToOne
    @OneToOne(mappedBy = "detail")
    @JsonIgnore // Ngăn vòng lặp vô hạn khi trả về JSON
    private Book book;

    public Detail() {}

    public Detail(Long detailid, String describe, String publisher, String publishing, Integer numberofpage,
                  String isbn, String format, Book book) {
        this.detailid = detailid;
        this.describe = describe;
        this.publisher = publisher;
        this.publishing = publishing;
        this.numberofpage = numberofpage;
        this.isbn = isbn;
        this.format = format;
        this.book = book;
    }

    // --- getters & setters ---
    
    public Long getDetailid() { return detailid; }
    public void setDetailid(Long detailid) { this.detailid = detailid; }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public String getPublishing() { return publishing; }
    public void setPublishing(String publishing) { this.publishing = publishing; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public Integer getNumberofpage() {
        return numberofpage;
    }

    public void setNumberofpage(Integer numberofpage) {
        this.numberofpage = numberofpage;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
