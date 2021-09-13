package com.example.airline.controller;


import com.example.airline.dto.AviaRaceDto;
import com.example.airline.dto.BestRouteDto;
import com.example.airline.service.AviaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/avia")
@RestController
public class AviaController {


    private final AviaService aviaService;

    @Autowired
    public AviaController(AviaService aviaService) {
        this.aviaService = aviaService;
    }

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(aviaService.findAll());
    }

    @PostMapping("/find")
    public ResponseEntity<?> findBestFlight(@RequestParam(value = "departurePoint") String departurePoint,
                                            @RequestParam(value = "destinationPoint") String destinationPoint,
                                            @RequestParam(value = "company") String[] company) {
        return ResponseEntity.ok(aviaService.findBestFlight(departurePoint, destinationPoint, Arrays.asList(company)));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AviaRaceDto> findOne(@PathVariable Long id) {
        AviaRaceDto dto = aviaService.findOne(id);
        return ResponseEntity.ok(dto);
    }
}
