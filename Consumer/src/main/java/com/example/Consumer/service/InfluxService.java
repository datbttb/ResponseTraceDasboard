package com.example.Consumer.service;

import java.util.Map;

public interface InfluxService {
    Boolean insert(String bucket, String measurement, Map<String, String> tags, Map<String, Object> fields, Long time);
}
