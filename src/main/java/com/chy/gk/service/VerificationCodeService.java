package com.chy.gk.service;

public interface VerificationCodeService {
    public String buildVerificationCode(String phoneNum);
    public String getVerificationCode(String phoneNum);
}
