package com.example.backend.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.Model.Book;
import com.example.backend.Model.BookChapter;
import com.example.backend.Model.Category;
import com.example.backend.Model.Detail;
import com.example.backend.Repository.DataRepositoryBook;
import com.example.backend.Repository.DataRepositoryCategory;
import com.example.backend.services.CloudinaryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private DataRepositoryBook book;
    @Autowired
    private DataRepositoryCategory category;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/call")
    public List<Book> getMethodName() {
        return book.findAll();
    }

    @GetMapping("/{category}")
    public List<Book> getMethodName(@PathVariable("category") Integer category) {
        return book.findCategory(category);
    }

    @GetMapping("/delete/{id}")
    public void getMethodNamee(@PathVariable("id") Integer param) {
        book.getBookDelete(param);
    }

    @PostMapping(value = "/create", consumes = { "multipart/form-data" })
    public ResponseEntity<Book> createBook(
            @RequestPart(value = "data", required = false) String data,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            // ✅ Convert JSON String → Book object
            ObjectMapper mapper = new ObjectMapper();
            Book entity = mapper.readValue(data, Book.class);

            // ✅ Upload ảnh lên Cloudinary nếu có file
            if (file != null && !file.isEmpty()) {
                String imageUrl = cloudinaryService.uploadImage(file);
                entity.setImg(imageUrl);
            }

            // ✅ Thiết lập quan hệ 1-nhiều giữa Book và BookChapter
            if (entity.getBookChapters() != null) {
                for (BookChapter ch : entity.getBookChapters()) {
                    ch.setBook(entity);
                }
            }

            // ✅ Thiết lập Detail (nếu có) để lưu kèm
            if (entity.getDetail() != null) {
                entity.getDetail().setBook(entity); // nếu anh dùng @OneToOne(mappedBy="book")
            }

            // ✅ Lưu vào database
            Book savedBook = book.save(entity);

            // ✅ Trả về kết quả
            return ResponseEntity.ok(savedBook);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/edit/{id}")
    public Book getMethodNameee(@PathVariable("id") Integer id) {
        return book.findEdit(id);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateBook(
            @PathVariable("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("author") String author,
            @RequestParam("describe") String describe,
            @RequestParam("price") Integer price,
            @RequestParam("rate") int rate,
            @RequestParam("publisher") String publisher,
            @RequestParam("publishing") String publishing,
            @RequestParam("numberOfPages") Integer numberOfPages,
            @RequestParam("categoryid") Long categoryid,
            @RequestParam("is_roles") String roles,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {

        try {
            // ✅ Tìm sách theo ID
            Book bookk = book.findById(id)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy sách có ID = " + id));

            // ✅ Cập nhật thông tin cơ bản
            bookk.setName(name);
            bookk.setAuthor(author);
            bookk.setDescribe(describe);
            bookk.setPrice(price);
            bookk.setRate(rate);
            bookk.setUpdated_at(new Date());
            bookk.setIs_roles(roles.toUpperCase());
            // ✅ Cập nhật chi tiết sách
            Detail detail = bookk.getDetail();
            if (detail == null)
                detail = new Detail();
            detail.setPublisher(publisher);
            detail.setPublishing(publishing);
            detail.setNumberofpage(numberOfPages);
            bookk.setDetail(detail);

            // ✅ Cập nhật thể loại
            Category categoryy = category.findById(categoryid)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy thể loại có ID = " + categoryid));
            bookk.setCategory(categoryy);

            // ✅ Nếu có ảnh mới → upload Cloudinary
            if (imageFile != null && !imageFile.isEmpty()) {
                String imageUrl = cloudinaryService.uploadImage(imageFile);
                bookk.setImg(imageUrl);
            }

            // ✅ Lưu thay đổi vào DB
            Book savedBook = book.save(bookk);

            return ResponseEntity.ok(savedBook);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Lỗi khi cập nhật: " + e.getMessage());
        }
    }
}
