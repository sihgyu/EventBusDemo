package com.sihgyu.eventbustest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class EventBusService {
    private EventBus eventBus;

    @Autowired
    ListerService listerService;
    @PostConstruct
    private void init(){
        eventBus = new AsyncEventBus(Executors.newFixedThreadPool(4));
        eventBus.register(listerService);
    }

    /**
     * 执行
     * @param integer
     */
    public void doEventBus(Integer integer){
        log.info("doEventBus --> {}",integer);
        eventBus.post(integer);
    }


}
