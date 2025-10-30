package com.example.backend.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Shipping")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shippingid; // ID tự tăng

    @Column(name = "shipperid", nullable = false)
    private Long shipperid; // ID shipper

    @OneToOne
    @JoinColumn(name = "billid", referencedColumnName = "billid")
    private Bill bill; // Liên kết tới Bill

    @Column(nullable = false)
    private String address; // Địa chỉ giao hàng

    @Column(nullable = false)
    private String status = "PENDING"; // Trạng thái giao hàng

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false; // Xóa mềm

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now(); // Ngày tạo

    // Getters & Setters
    public Long getShippingid() {
        return shippingid;
    }

    public void setShippingid(Long shippingid) {
        this.shippingid = shippingid;
    }

    public Long getShipperid() {
        return shipperid;
    }

    public void setShipperid(Long shipperid) {
        this.shipperid = shipperid;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
