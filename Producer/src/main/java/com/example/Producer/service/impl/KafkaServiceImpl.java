package com.example.Producer.service.impl;

import com.example.Producer.data.ResponseTrace;
import com.example.Producer.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public boolean saveResponseTrace(ResponseTrace responseTrace) {
        try{
            boolean check = true;
            CompletableFuture<SendResult<String, Object>> completableFuture = kafkaTemplate.send("responseTrace",responseTrace);
            completableFuture.whenComplete((result,ex) -> {
               if(ex==null){
               }
               else {
                   ex.printStackTrace();
               }
            });
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
