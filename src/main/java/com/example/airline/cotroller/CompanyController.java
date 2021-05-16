package com.example.airline.cotroller;

import com.example.airline.dto.AirCompanyDto;
import com.example.airline.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService service){
        this.companyService = service;
    }

    @GetMapping
    public List<AirCompanyDto> getAllPorts(){
        return companyService.findAll();
    }
}
