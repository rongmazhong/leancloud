package com.smepublish.demo.producer;

import com.google.common.collect.Lists;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;

/**
 * 〈消息发送者〉
 *
 * @author mazhongrong@smeyun.com
 * @date 2019/1/16
 */
@Configuration
public class SmsProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsProducer.class);

    // /**
    //  * 是否开启 SDK调试日志
    //  */
    // @Value("${myApp.isDebug}")
    // private static boolean isDebug;

    @Value("${myApp.APP_SECRET_KEY}")
    private String APP_SECRET_KEY;
    //
    @Value("${myApp.MY_PACKAGE_NAME}")
    private String MY_PACKAGE_NAME;


    /**
     * sendMsg 安卓发送消息
     *
     * @param type
     * @param aliasList
     * @param msg
     * @return com.xiaomi.xmpush.server.Result
     * @author MZRong
     * @date 2019/1/21 10:08
     */
    public Result sendMsg(String type, List<String> aliasList, String msg) throws IOException, ParseException {

        System.out.println("type:" + type + ";message:" + msg);
        Constants.useOfficial();
        Sender sender = new Sender(APP_SECRET_KEY);
        String messagePayload = "This is a message";
        String title = "notification title";
        String description = "notification description";
        Message message = new Message.Builder().title(title).description(description).payload(messagePayload)
                .restrictedPackageName(MY_PACKAGE_NAME)
                .passThrough(0)
                .notifyType(1)
                .build();

        Result result = sender.send(message, aliasList, 3);
        LOGGER.info("Server response: ", "MessageId: " + result.getMessageId()
                + " ErrorCode: " + result.getErrorCode().toString()
                + " Reason: " + result.getReason());
        return result;
    }

    public Result sendIosMsg(String type, List<String> aliasList, String msg) throws IOException, ParseException {
        Constants.useOfficial();
        Sender sender = new Sender(APP_SECRET_KEY);
        String messagePayload = "This is a message";
        String title = "notification title";
        String description = "notification description";
        Message message = new Message.Builder()
                .title(title)
                .description(description).payload(messagePayload)
                .restrictedPackageName(MY_PACKAGE_NAME)
                // 使用默认提示音提示
                .notifyType(1)
                .build();
        Result result = sender.send(message, aliasList, 3);
        return result;
    }

}
