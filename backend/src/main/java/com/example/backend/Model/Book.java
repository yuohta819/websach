package com.example.backend.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookid;

    private String name;
    private String author;
    private int rate;
    private Integer price;
    private String describe;
    private String img;

    // ✅ Một Book có 1 Detail
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detailid", referencedColumnName = "detailid")
    private Detail detail;

    // ✅ Một Book có nhiều Chapter
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BookChapter> bookChapters = new ArrayList<>();

    // ✅ Một Book thuộc về 1 Category
    @ManyToOne
    @JoinColumn(name = "categoryid", referencedColumnName = "categoryid")
    private Category category;

    private Boolean is_deleted = false;
    private Date created_at = new Date();
    private Date updated_at = new Date();
    private String is_roles;

    public Book() {
    }

    public Book(String name, String author, int rate, Integer price, String describe,
            String img, Detail detail, List<BookChapter> bookChapters, Category category, String is_roles) {
        this.name = name;
        this.author = author;
        this.rate = rate;
        this.price = price;
        this.describe = describe;
        this.img = img;
        this.detail = detail;
        this.bookChapters = bookChapters;
        this.category = category;
        this.is_roles = is_roles;
    }

    public Book(Long bookid, String name, String author, int rate, Integer price, String describe, String img,
            Category category, Boolean is_deleted, Date created_at, Date updated_at, String is_roles) {
        this.bookid = bookid;
        this.name = name;
        this.author = author;
        this.rate = rate;
        this.price = price;
        this.describe = describe;
        this.img = img;
        this.category = category;
        this.is_deleted = is_deleted;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.is_roles = is_roles;

    }

    // ✅ Getter & Setter
    
    public Long getBookid() {
        return bookid;
    }

    public void setBookid(Long bookid) {
        this.bookid = bookid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public List<BookChapter> getBookChapters() {
        return bookChapters;
    }

    public void setBookChapters(List<BookChapter> bookChapters) {
        this.bookChapters = bookChapters;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getIs_roles() {
        return is_roles;
    }

    public void setIs_roles(String is_roles) {
        this.is_roles = is_roles;
    }

  
}
