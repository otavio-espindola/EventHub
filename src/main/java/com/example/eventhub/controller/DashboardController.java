package com.example.eventhub.controller;

import com.example.eventhub.dto.dashboard.*;
import com.example.eventhub.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/dashboard")
@RestController
public class DashboardController {
    private final DashboardService service;

    public DashboardController(DashboardService service){
        this.service = service;
    }
    @GetMapping
    public DashboardResponse dashboard() {
        return service.dashboard();
    }
}
