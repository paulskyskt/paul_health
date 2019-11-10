package com.paul.daotest;

import com.aliyuncs.exceptions.ClientException;
import com.paul.utils.SMSUtils;
import org.junit.Test;

import java.util.Date;

public class TestSMS {

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
