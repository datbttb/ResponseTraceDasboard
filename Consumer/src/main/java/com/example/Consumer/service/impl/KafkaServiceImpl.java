package com.example.Consumer.service.impl;

import com.example.Consumer.data.ResponseTrace;
import com.example.Consumer.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    InfluxServiceImpl influxService;


    @KafkaListener(id = "responseTraceGroup", topics = "responseTrace")
    @Override
    public void readKafkaWriteInflux(ResponseTrace responseTrace) {
        String bucket = "ResponseTrace";
        String measurement = "respone_trace";
        Map<String, String> tags = new HashMap<>();
        tags.put("service_name",responseTrace.getServiceName());
        tags.put("request_id",responseTrace.getRequestId());
        tags.put("function_name", responseTrace.getFunctionName());
        tags.put("pod_name", responseTrace.getPodName());
        tags.put("response_code",responseTrace.getResponseCode());
        tags.put("response_desc", responseTrace.getResponseDesc());
        Map<String, Object> fields = new HashMap<>();
        fields.put("cost",responseTrace.getCost());
        Long time = responseTrace.getRequestTime();
        Boolean check = influxService.insert(bucket,measurement,tags,fields,time);
        System.out.println(check);
    }
}
