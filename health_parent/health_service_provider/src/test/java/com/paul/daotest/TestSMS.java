package com.paul.daotest;

import com.aliyuncs.exceptions.ClientException;
import com.paul.utils.SMSUtils;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

public class TestSMS {

    @Test
    public void test3(){
        BCryptPasswordEncoder bb = new BCryptPasswordEncoder();
        String pwd = bb.encode("123");
        String pwd1 = bb.encode("123");
        System.out.println(pwd);
        System.out.println(pwd1);

    }

    @Test
    public void test2(){
        try {
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,"15823192046","4032");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1(){

        try {
            SMSUtils.sendShortMessage("SMS_177241133","15823192046","2019-11-1");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
