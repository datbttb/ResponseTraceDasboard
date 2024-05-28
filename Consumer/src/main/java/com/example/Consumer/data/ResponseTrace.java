package com.example.Consumer.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ResponseTrace {
    private static final long serialVersionUID = 1L;
    private String serviceName;
    private String requestId;
    private String functionName;
    private long requestTime;
    private String podName;
    private long cost;
    private String responseCode;
    private String responseDesc;
}