package com.example.eventhub.controller;

import com.example.eventhub.service.InscricaoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inscricao")
public class InscricaoController {
    private final InscricaoService incricaoService;

    public InscricaoController(InscricaoService service){
        this.incricaoService = service;
    }
}
