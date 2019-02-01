package com.smepublish.demo.controller;

import com.smepublish.demo.producer.SmsProducer;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈消息推送〉
 *
 * @author mazhongrong@smeyun.com
 * @date 2019/1/16
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    public SmsProducer smsProducer;

    @PostMapping(value = "/sendMsg/")
    public Object sendMsg(String msg) {
        LOGGER.info("发送消息：" + msg);
        String type = "android";
        List<String> aliasList = new ArrayList<>();
        aliasList.add("123456567");
        aliasList.add("234567");
        Object data = "";
        try {
            data = smsProducer.sendMsg(type, aliasList, msg);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }
}
