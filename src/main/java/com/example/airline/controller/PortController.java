package com.example.airline.controller;

import com.example.airline.dto.AirPortDto;
import com.example.airline.service.PortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/port")
public class PortController {

    private PortService portService;

    @Autowired
    public PortController(PortService service){
        this.portService = service;
    }

    @GetMapping
    public List<AirPortDto> getAllPorts(){
        return portService.findAll();
    }
}
