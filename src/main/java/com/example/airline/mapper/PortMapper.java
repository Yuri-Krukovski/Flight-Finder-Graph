package com.example.airline.mapper;

import com.example.airline.dto.AirPortDto;
import com.example.airline.model.AirPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PortMapper extends AbstractMapper<AirPort, AirPortDto> {

    @Autowired
    public PortMapper(){
        super(AirPort.class, AirPortDto.class);
    }
}


