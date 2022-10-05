package com.example.rest_tz.controller;

import com.example.rest_tz.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {

    InputService inputService;

    @Autowired
    public MainController(InputService inputService) {
        this.inputService = inputService;
    }

    @GetMapping("/analyze/{input}")
    public ResponseEntity<Map<String, ArrayList<Integer>>> stringAnalyze(@PathVariable("input") String input) {
        return new ResponseEntity<>(inputService.stringAnalyze(input), HttpStatus.OK);
    }

    @GetMapping("/statistic")
    public ResponseEntity<Map<String, ArrayList<Double>>> statistic() {
        return new ResponseEntity<>(inputService.statistic(), HttpStatus.OK);
    }
}
