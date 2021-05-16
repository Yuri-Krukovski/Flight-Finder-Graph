package com.example.airline.dao;

import com.example.airline.model.AirCompany;
import com.example.airline.model.AirPort;
import com.example.airline.model.AviaRace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AirRepository extends JpaRepository<AviaRace, Long> {
    List<AviaRace> findAllByDeparturePointName(String dPoint);

    List<AviaRace> findByDestinationPointNameLikeOrDeparturePointNameLike(String dPoint, String desPoint);

    AviaRace findByDeparturePointAndDestinationPoint(AirPort depPoint, AirPort destPoint);

    List<AviaRace> findAllByCompanyNameIn(List<String> company);


    List<AviaRace> findAllByDeparturePointOrDestinationPoint(AirPort departurePoint, AirPort destinationPoint);

    List<AviaRace> findAllByDeparturePoint(AirPort departurePoint);

    Optional<AviaRace> findByDistance(int distance);

    Optional<AviaRace> findByPrice(int price);
}
