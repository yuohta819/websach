package com.example.backend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.backend.Model.UserSession;

import jakarta.transaction.Transactional;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
    // 🔹 Lấy session theo accountid
    @Query("SELECT u FROM UserSession u WHERE u.accountid = :accountid")
    Optional<UserSession> findByAccountid(@Param("accountid") Long accountid);

    // 🔹 Xóa session theo accountid
    @Transactional
    @Modifying
    @Query("DELETE FROM UserSession u WHERE u.accountid = :accountid")
    void deleteByAccountid(@Param("accountid") Long accountid);

    Optional<UserSession> findByToken(String token);

    @Query("SELECT u FROM UserSession u WHERE u.token = :token")
    UserSession findToken(@Param("token") String token);

}