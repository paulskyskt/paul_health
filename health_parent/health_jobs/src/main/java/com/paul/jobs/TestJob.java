package com.paul.jobs;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Lazy(false)
public class TestJob {

    @Scheduled(cron = "0/1 * * * * ?")
    public void test(){
        System.out.println("1");
    }



}
