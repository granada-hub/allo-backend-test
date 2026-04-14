package com.allobank.devtest.controller;

import com.allobank.devtest.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/finance")
public class FinanceController {

    private final FinanceService service;

    @GetMapping("/data/{resourceType}")
    public ResponseEntity<?> getData(@PathVariable String resourceType) {
        return ResponseEntity.ok(service.getData(resourceType));
    }
}