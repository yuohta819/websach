package com.example.backend.Repository;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.backend.Model.Roles;

import jakarta.transaction.Transactional;

public interface DataRepositoryRoles extends JpaRepository<Roles, Long> {
        @Query("SELECT d FROM Roles d WHERE d.account.accountid = :id")
        Roles getAccount(@Param("id") Long id);

        @Transactional
        @Modifying
        @Query("UPDATE Roles r SET r.pro = TRUE, r.expired_date = :expiredTime,r.create_at = :createdAt WHERE r.account.accountid = :id")
        void updateRolePro(@Param("id") Integer id,
                        @Param("expiredTime") Date expiredTime,
                        @Param("createdAt") Date createdAt);

        @Transactional
        @Modifying
        @Query("UPDATE Roles r SET r.premium = TRUE, r.expired_date = :expiredTime,r.create_at = :createdAt WHERE r.account.accountid = :id")
        void updateRolePremium(@Param("id") Integer id,
                        @Param("expiredTime") Date expiredTime,
                        @Param("createdAt") Date createdAt);

        @Transactional
        @Modifying
        @Query("UPDATE Roles SET premium = FALSE, pro = FALSE,expired_date = null, create_at=null WHERE account.accountid=:id")
        void updateAll(@Param("id") Integer id);
}
