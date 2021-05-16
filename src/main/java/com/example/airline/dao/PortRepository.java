package com.example.airline.dao;

import com.example.airline.model.AirPort;
import com.example.airline.model.AviaRace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortRepository extends JpaRepository<AirPort, Long> {
    Optional<AirPort> findByName(String name);
}
