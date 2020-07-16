package com.phantom5702.sms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@Slf4j
public class TestController {


    @GetMapping("/test")
    public String est() {
        return "hello2";
    }

    @GetMapping("/test2")
    public String add(Integer[] mallid, Integer userId, Integer addressId) {
//        log.info("mallid {} userId is {} addressId is {}",mallid,userId,addressId);
        return "hello:" + userId + "," + addressId;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("D:/test.txt");
        OutputStream out = new FileOutputStream(file);
        for(int i =0;i<10000;i++){
            out.write((i+","+i+","+i+"\n").getBytes());
        }
        out.flush();
        out.close();
    }


}
