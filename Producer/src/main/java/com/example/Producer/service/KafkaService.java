package com.example.Producer.service;

import com.example.Producer.data.ResponseTrace;

public interface KafkaService {

    boolean saveResponseTrace(ResponseTrace responseTrace);

}
