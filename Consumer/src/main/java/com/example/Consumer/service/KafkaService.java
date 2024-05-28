package com.example.Consumer.service;

import com.example.Consumer.data.ResponseTrace;

public interface KafkaService {

    void readKafkaWriteInflux(ResponseTrace responseTrace);

}
