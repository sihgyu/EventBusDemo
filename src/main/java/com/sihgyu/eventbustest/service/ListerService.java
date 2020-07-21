package com.sihgyu.eventbustest.service;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ListerService {

    @Subscribe
    public void lister(Integer integer){
        System.out.println(integer);
        log.info("lister has receive a message :{}",integer);
    }
}
