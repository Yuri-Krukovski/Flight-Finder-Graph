package com.example.airline.service;

import com.example.airline.dao.CompanyRepository;
import com.example.airline.dto.AirCompanyDto;
import com.example.airline.mapper.AviaCompanyMapper;
import com.example.airline.model.AirCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final AviaCompanyMapper mapper;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, AviaCompanyMapper mapper) {
        this.companyRepository = companyRepository;
        this.mapper = mapper;
    }

    public void addCompany(AirCompany company){
        companyRepository.save(company);
    }

    public Optional<AirCompany> findByName(String name) {

        return companyRepository.findByName(name);
    }


    public List<AirCompanyDto> findAll() {
        return companyRepository
                .findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


}
