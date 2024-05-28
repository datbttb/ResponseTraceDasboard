package com.example.Consumer.service.impl;

import com.example.Consumer.service.InfluxService;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Service
public class InfluxServiceImpl implements InfluxService {

    @Value("${spring.influx.org}")
    private String ORG;

    @Autowired
    private InfluxDBClient influxDBClient;
    @Override
    public Boolean insert(String bucket, String measurement, Map<String, String> tags, Map<String, Object> fields, Long time) {
        try {
            Date date = new Date(time);
            Instant timeReal = date.toInstant();
            Point point = Point
                    .measurement(measurement)
                    .addTags(tags)
                    .addFields(fields)
                    .time(timeReal, WritePrecision.NS);
            influxDBClient.getWriteApiBlocking().writePoint(bucket,ORG,point);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
