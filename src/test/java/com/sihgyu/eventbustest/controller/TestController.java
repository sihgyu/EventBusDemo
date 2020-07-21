package com.sihgyu.eventbustest.controller;


import com.sihgyu.eventbustest.service.AuthService;
import com.sihgyu.eventbustest.service.EventBusService;
import com.sihgyu.eventbustest.service.OcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@ResponseBody
public class TestController {
    @Autowired
    private AuthService authService;
    @Autowired
    private OcrService ocrService;

    @Autowired
    private EventBusService eventBusService;
    @GetMapping("get_auth")
    public String getAuth(){
        String token = authService.getAuth();
        return token;
    }

    @RequestMapping(path = "test_ocr",method = RequestMethod.POST)
    public String testOcr(){
        return ocrService.generalBasic();
    }

    @RequestMapping(path = "testBus",method = RequestMethod.POST)
    public void testEvent(@RequestParam("int") Integer integer){
        eventBusService.doEventBus(integer);
    }
}
