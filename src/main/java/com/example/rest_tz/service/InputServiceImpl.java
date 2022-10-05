package com.example.rest_tz.service;

import com.example.rest_tz.model.InputData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class InputServiceImpl implements InputService {

    InputData inputData = new InputData();

    @Override
    public Map<String, ArrayList<Integer>> stringAnalyze(String input) {
        Map<String, ArrayList<Integer>> result = new HashMap<>();
        int counter = 1;
        for (int i = 0; i < input.length(); i++) {
            String key = Character.toString(input.charAt(i));

            if (inputData.getRequestCount() == null) {
                HashMap<String, Double> requestCount = new HashMap<>();
                requestCount.put(key, 0.0);

                HashMap<String, Double> countPerLine = new HashMap<>();
                countPerLine.put(key, 0.0);

                HashMap<String, Double> averageLenghtMax = new HashMap<>();
                averageLenghtMax.put(key, 0.0);

                inputData.setRequestCount(requestCount);
                inputData.setAllCountPerLine(countPerLine);
                inputData.setAllLenghtMax(averageLenghtMax);
            }

            if (i > 0 && key.equals(Character.toString(input.charAt(i - 1)))) {
                counter++;
            } else {
                counter = 1;
            }

            if (!result.containsKey(key)) {
                result.put(key, new ArrayList<>(Arrays.asList(1, 1)));

                if (!inputData.getRequestCount().containsKey(key)) {
                    inputData.getRequestCount().put(key, 0.0);
                    inputData.getAllCountPerLine().put(key, 0.0);
                    inputData.getAllLenghtMax().put(key, 0.0);
                }
            } else {
                if (counter > result.get(key).get(1)) {
                    result.put(key, new ArrayList<>(Arrays.asList(result.get(key).get(0) + 1, counter)));
                } else {
                    result.put(key, new ArrayList<>(Arrays.asList(result.get(key).get(0) + 1, result.get(key).get(1))));
                }

                inputData.getRequestCount().put(key, inputData.getRequestCount().get(key) + 1);
                inputData.getAllCountPerLine().put(key, inputData.getAllCountPerLine().get(key) + result.get(key).get(0));
                inputData.getAllLenghtMax().put(key, inputData.getAllLenghtMax().get(key) + result.get(key).get(1));
            }
        }
        return result;
    }

    @Override
    public Map<String, ArrayList<Double>> statistic() {
        Map<String, ArrayList<Double>> result = new HashMap<>();
        for (Map.Entry<String, Double> entry : inputData.getRequestCount().entrySet()) {
            result.put(entry.getKey(), new ArrayList<>(Arrays.asList(
                    inputData.getRequestCount().get(entry.getKey()),
                    inputData.getAllCountPerLine().get(entry.getKey()) / inputData.getRequestCount().get(entry.getKey()),
                    inputData.getAllLenghtMax().get(entry.getKey()) / inputData.getRequestCount().get(entry.getKey())
            )));
        }
        return result;
    }
}
