package com.fsecure.monitor.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Matrix {
    private String aTime;
    private String url;
    private String contentMatched;
    private double requestTime;
    private int httpResponseCode;
    private String httpResponseMessage;
}
