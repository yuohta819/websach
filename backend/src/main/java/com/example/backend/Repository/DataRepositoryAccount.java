package com.example.backend.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.backend.Model.Account;

import jakarta.transaction.Transactional;

public interface DataRepositoryAccount extends JpaRepository<Account, Long> {
    List<Account> findAll();

    @Query("SELECT a FROM Account a WHERE a.email = :email")
    Account getAccount(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("UPDATE Account SET money = :money WHERE accountid = :id")
    void updateMoney(@Param("money") Integer money, @Param("id") Integer id);

    @Query("SELECT a.money FROM Account a WHERE a.accountid = :id")
    Integer findMoneyByAccountId(@Param("id") Integer id);

}
