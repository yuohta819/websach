package com.example.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.backend.Model.Cart;
import com.example.backend.Model.Roles;

import jakarta.transaction.Transactional;

public interface DataRepositoryCart extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.accountid = :id")
    List<Cart> getAllFind(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Cart WHERE cartid = :id")
    void deleteCart(@Param("id") Integer id);

    @Query("SELECT r FROM Roles r WHERE r.account.accountid = :id AND r.member = TRUE")
    Roles checkMember(@Param("id") Integer id);

    @Query("SELECT r FROM Roles r WHERE r.account.accountid = :id AND r.pro = TRUE")
    Roles checkPro(@Param("id") Integer id);

    @Query("SELECT r FROM Roles r WHERE r.account.accountid = :id AND r.premium = TRUE AND r.pro = TRUE AND r.member = TRUE")
    Roles checkPremium(@Param("id") Integer id);
}
