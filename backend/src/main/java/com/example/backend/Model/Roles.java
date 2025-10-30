package com.example.backend.Model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Calendar;

@Entity
@Table(name = "Roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleid;

    @OneToOne
    @JoinColumn(name = "accountid", referencedColumnName  = "accountid")
    private Account account;

    private boolean member = true;
    private boolean pro = false;
    private boolean premium = false;

    @Temporal(TemporalType.TIMESTAMP)
    private Date create_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expired_date;

    public Roles() {}

    public Roles(Long roleid, Account account, boolean member, boolean pro, boolean premium) {
        this.roleid = roleid;
        this.account = account;
        this.member = member;
        this.pro = pro;
        this.premium = premium;
    }

    // ✅ Tự động set create_at và expired_date khi thêm mới
    @PrePersist
    protected void onCreate() {
        this.create_at = new Date(); // thời điểm hiện tại

        // cộng thêm 30 ngày
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.create_at);
        cal.add(Calendar.DAY_OF_MONTH, 30);
        this.expired_date = cal.getTime();
    }

    // Getters & Setters
    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    public boolean isPro() {
        return pro;
    }

    public void setPro(boolean pro) {
        this.pro = pro;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(Date expired_date) {
        this.expired_date = expired_date;
    }
}
