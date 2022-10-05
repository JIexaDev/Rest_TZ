package com.example.rest_tz.model;

import lombok.Data;

import java.util.Map;

@Data
public class InputData {
    Map<String, Double> requestCount;
    Map<String, Double> allCountPerLine;
    Map<String, Double> allLenghtMax;
}
