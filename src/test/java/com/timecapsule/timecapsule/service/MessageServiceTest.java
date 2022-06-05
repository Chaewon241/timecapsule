package com.timecapsule.timecapsule.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageServiceTest {

    @Autowired MessageService messageService;

    @Test
    public void 메시지테스트() throws Exception{
//        messageService.sendMessage("01054079254");
    }


}