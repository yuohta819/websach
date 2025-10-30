package com.example.backend.Model;


public class MetaMaskLoginRequest {
    private String walletAddress;
    private String signature;
    public MetaMaskLoginRequest() {
    }
    public MetaMaskLoginRequest(String walletAddress, String signature) {
        this.walletAddress = walletAddress;
        this.signature = signature;
    }
    public String getWalletAddress() {
        return walletAddress;
    }
    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }
    public String getSignature() {
        return signature;
    }
    public void setSignature(String signature) {
        this.signature = signature;
    }
    
}
