package com.example.backend.Model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billid; // Khóa chính tự tăng

    @Column(name = "bill_code", nullable = false, unique = true)
    private String billCode; // Mã hóa đơn

    @Column(name = "bookid", nullable = false)
    private Long bookid; // Mã sách

    @Column(name = "accountid", nullable = false)
    private Long accountid; // Mã tài khoản

    @Column(nullable = false)
    private int quantity; // Số lượng mua

    @Column(name = "purchase_time")
    private LocalDateTime purchaseTime = LocalDateTime.now(); // Thời gian mua

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false; // Cờ xóa mềm

    // Quan hệ 1-1 với Shipping
    @OneToOne(mappedBy = "bill", cascade = CascadeType.ALL)
    private Shipping shipping;

    // Getters & Setters
    public Long getBillid() {
        return billid;
    }

    public void setBillid(Long billid) {
        this.billid = billid;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public Long getBookid() {
        return bookid;
    }

    public void setBookid(Long bookid) {
        this.bookid = bookid;
    }

    public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }
}
