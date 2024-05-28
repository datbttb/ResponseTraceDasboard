package com.example.Producer.controller;

import com.example.Producer.data.ResponseTrace;
import com.example.Producer.service.KafkaService;
import com.example.Producer.service.impl.KafkaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@RestController
@RequestMapping("api/responetrace")
@RequiredArgsConstructor
@CrossOrigin
public class ResponseTraceController {
    @Autowired
    KafkaServiceImpl kafkaService;

    @GetMapping("readfile")
    public ResponseEntity<?> readFile(){
        try {
            BufferedReader reader2 = new BufferedReader(new FileReader("files/data.csv"));
            String line = null; //line read from csv
            Scanner scanner = null; //scanned line
            while ((line = reader2.readLine()) != null) {
                scanner = new Scanner(line);
                scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    scanner.next();
                    ResponseTrace responseTrace = new ResponseTrace();
                    responseTrace.setServiceName(scanner.next());
                    responseTrace.setRequestId(scanner.next());
                    responseTrace.setFunctionName(scanner.next());
                    responseTrace.setRequestTime(Long.parseLong(scanner.next()));
                    responseTrace.setPodName(scanner.next());
                    responseTrace.setCost(Long.parseLong(scanner.next()));
                    responseTrace.setResponseCode(scanner.next());
                    responseTrace.setResponseDesc(scanner.next());
                    Boolean check = kafkaService.saveResponseTrace(responseTrace);
                }
            }
            scanner.close();
            reader2.close();
            return ResponseEntity.ok().body("Read completed");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
