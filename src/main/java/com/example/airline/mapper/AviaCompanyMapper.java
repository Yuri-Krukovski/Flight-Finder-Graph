package com.example.airline.mapper;

import com.example.airline.dto.AirCompanyDto;
import com.example.airline.model.AirCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AviaCompanyMapper extends AbstractMapper <AirCompany, AirCompanyDto>{

    @Autowired
    public AviaCompanyMapper(){
        super(AirCompany.class, AirCompanyDto.class);
    }
}
