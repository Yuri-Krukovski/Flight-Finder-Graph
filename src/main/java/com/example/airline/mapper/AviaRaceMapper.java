package com.example.airline.mapper;

import com.example.airline.dto.AviaRaceDto;
import com.example.airline.model.AviaRace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AviaRaceMapper extends AbstractMapper<AviaRace, AviaRaceDto> {

    @Autowired
    public AviaRaceMapper(){
        super(AviaRace.class, AviaRaceDto.class);
    }
}
