package com.timecapsule.timecapsule.service;

import com.timecapsule.timecapsule.MailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service("mss")
@RequiredArgsConstructor
public class MailSendService {

    private final JavaMailSender mailSender;
    private int size;

    /**
     * 인증키 생성
     */
    private String getKey(int size){
        this.size = size;
        return getAuthCode();
    }

    /**
     * 인증코드 난수 발생
     */
    private String getAuthCode(){
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int num = 0;

        while(buffer.length() < size){
            num = random.nextInt(10);
            buffer.append(num);
        }

        return buffer.toString();
    }

    /**
     * 인증메일 보내기
     */
    public String sendAuthMail(String email){
        //6자리 난수 인증번호 생성
        String authKey = getKey(6);

        //인증메일 보내기
        try{
            MailUtils sendMail = new MailUtils(mailSender);
            sendMail.setSubject("회원가입 이메일 인증");
            sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
                    .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.<p>")
                    .append("<a href='http://localhost:8080/member/signUpConfirm?email=")
                    .append(email)
                    .append("&authKey=")
                    .append(authKey)
                    .append("' target='_blank'>이메일 인증 확인</a>")
                    .toString());
            sendMail.setFrom("[인증 메일 전송하는 이메일 주소]", "[관리자, 이메일에 표시되는 이름]");
            sendMail.setTo(email);
            sendMail.send();
        } catch (MessagingException e){
            e.printStackTrace();
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        return authKey;
    }

    public void sendAlarm(String email){
        try{
            MailUtils sendMail = new MailUtils(mailSender);
            sendMail.setSubject("웹타임캡슐");
            sendMail.setText(new StringBuffer().append("<h1>웹 타임 캡슐</h1>")
                    .append("<p>오늘은 웹 타임캡슐을 확인하실 날이에요~!<p>")
                    .append("<p>아래 링크를 클릭하시면 웹타임캡슐 메인화면으로 이동합니다.<p>")
                    .append("<a href='http://localhost:8080")
                    .append("' target='_blank'>TimeCapsule</a>")
                    .toString());
            sendMail.setFrom("[인증 메일 전송하는 이메일 주소]", "[관리자, 이메일에 표시되는 이름]");
            sendMail.setTo(email);
            sendMail.send();
        } catch(MessagingException e){
            e.printStackTrace();
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }
}

