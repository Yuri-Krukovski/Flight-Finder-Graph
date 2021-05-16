package com.example.airline.dao;

import com.example.airline.model.AirCompany;
import com.example.airline.model.AirPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<AirCompany, Long> {

    Optional<AirCompany> findByName(String name);

}
