package com.example.airline.dto;

import java.time.LocalTime;
import java.util.Objects;

public class AviaRaceDto extends AbstractDto implements Comparable<AviaRaceDto> {


    private Long id;
    private LocalTime time;
    private String departurePoint;
    private String destinationPoint;
    private int distance;
    private int price;
    private String company;



    public AviaRaceDto() {
    }

    public AviaRaceDto(LocalTime time, String departurePoint, String destinationPoint, int distance, int price) {
        super();
        this.time = time;
        this.departurePoint = departurePoint;
        this.destinationPoint = destinationPoint;
        this.distance = distance;
        this.price = price;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "AviaRaceDto{" +
                "departurePoint='" + departurePoint + '\'' +
                ", destinationPoint='" + destinationPoint + '\'' +
                ", distance=" + distance +
                ", price=" + price +
                '}';
    }



    @Override
    public int compareTo(AviaRaceDto aviaRaceDto) {
        return this.getTime().compareTo(aviaRaceDto.getTime());
    }
}
