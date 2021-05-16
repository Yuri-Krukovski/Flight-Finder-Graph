package com.example.airline.dto;

import com.example.airline.model.AviaRace;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BestRouteDto {

    private List<AviaRaceDto> dtoList;

    private int totalPrice;
    private int totalDistance;


    public BestRouteDto() {
        this.dtoList = new ArrayList<>();
    }

    public void addRoute(AviaRaceDto race) {
        dtoList.add(race);
    }

    public int getTotalPrice() {
        for (AviaRaceDto race : dtoList) {
            totalPrice += race.getPrice();
        }
        return totalPrice;
    }

    public int getTotalDistance() {
        for (AviaRaceDto race : dtoList) {
            totalDistance += race.getDistance();
        }
        return totalDistance;
    }

    public List<AviaRaceDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<AviaRaceDto> dtoList) {
        this.dtoList = dtoList;
    }
}
