package com.timecapsule.timecapsule.controller;

import com.timecapsule.timecapsule.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    /**
     * 휴대폰 인증
     * 회원가입시에 휴대폰 인증을 받는 API 입니다.
     * authKey 와 휴대폰 번호로 작동하는 API 입니다.
     * @return : authKey 값을 JSON 형태로 반환합니다.
     */
    @PostMapping("member/phone")
    public MemberPhoneAuthResponse MemberPhone(
            @RequestBody @Valid MemberPhoneAuthRequest request) {
        messageService.sendAuthMessage(request.getPhoneNumber(), request.authKey);
        return new MemberPhoneAuthResponse(request.authKey);
    }

    @Data
    static class MemberPhoneAuthRequest{
        private String phoneNumber;
        private String authKey;
    }

    @Data
    @AllArgsConstructor
    static class MemberPhoneAuthResponse{
        private String authKey;
    }
}
