package com.timecapsule.timecapsule.service;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class MessageService {

    @Value("${Phone.apiKey}")
    private String apiKey;

    @Value("${Phone.apiSecret}")
    private String apiSecret;

    @Value("${Phone.fromNumber}")
    private String fromNumber;

    Message coolsms = new Message(apiKey, apiSecret);

    public String sendAuthMessage(String toNumber, String randomNumber){

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", toNumber);
        params.put("from", fromNumber);
        params.put("type", "SMS");
        params.put("text", "[웹타임캡슐]인증번호 " + randomNumber + " 를 입력하세요.");

        try{
            JSONObject obj = (JSONObject) coolsms.send(params);
            return obj.toString();
        } catch (CoolsmsException e){
            throw new IllegalStateException("전송 실패");
        }
    }

    public String sendMessage(String toNumber){

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", toNumber);
        params.put("from", fromNumber);
        params.put("type", "SMS");
        params.put("text", "[웹타임캡슐] 오늘은 타임 캡슐을 확인할 날이에요");

        try{
            JSONObject obj = (JSONObject) coolsms.send(params);
            return obj.toString();
        } catch (CoolsmsException e){
            throw new IllegalStateException("전송 실패");
        }
    }
}
