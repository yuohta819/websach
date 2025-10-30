package com.example.backend.Model;



import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "user_sessions")
public class UserSession {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionid;

    private Long accountid;
    private String token;
    private String device_info;
    private String ip_address;
    private Date created_at;
    public UserSession() {
    }
    public UserSession(Long sessionid, Long accountid, String token, String device_info, String ip_address,
            Date created_at) {
        this.sessionid = sessionid;
        this.accountid = accountid;
        this.token = token;
        this.device_info = device_info;
        this.ip_address = ip_address;
        this.created_at = created_at;
    }
    public Long getSessionid() {
        return sessionid;
    }
    public void setSessionid(Long sessionid) {
        this.sessionid = sessionid;
    }
    public Long getAccountid() {
        return accountid;
    }
    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getDevice_info() {
        return device_info;
    }
    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }
    public String getIp_address() {
        return ip_address;
    }
    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }
    public Date getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    
    
}
