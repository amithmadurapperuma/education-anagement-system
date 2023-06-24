package com.developersstack.edumanager.util.tools;

import java.util.Random;

public class VerificationCodeGenerator {
    public int verificationCde(){
        Random random = new Random();
        int code = random.nextInt((99999 - 10000) + 1) + 10000;
        return code;
    }
}
