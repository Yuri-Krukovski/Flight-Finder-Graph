package com.example.airline.service;

import com.example.airline.dao.PortRepository;
import com.example.airline.dto.AirPortDto;
import com.example.airline.mapper.PortMapper;
import com.example.airline.model.AirPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PortService {


    private PortRepository portRepository;
    private PortMapper mapper;

    @Autowired
    public PortService(PortRepository portRepository, PortMapper mapper) {
        this.portRepository = portRepository;
        this.mapper = mapper;
    }

    public void addPort(AirPort airport) {
        portRepository.save(airport);
    }

    public Optional<AirPort> findByName(String name) {

        return portRepository.findByName(name);
    }

    public List<AirPortDto> findAll() {
        return portRepository
                .findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
