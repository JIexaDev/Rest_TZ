package com.example.rest_tz.service;

import java.util.ArrayList;
import java.util.Map;

public interface InputService {
    Map<String, ArrayList<Integer>> stringAnalyze(String input);
    Map<String, ArrayList<Double>> statistic();
}
