package com.example.airline.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Entity
@Table(name = "avia_races")
public class AviaRace extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "flight_time")
    private LocalTime time;

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "race_company",
            joinColumns = @JoinColumn(name = "avia_races_id"),
            inverseJoinColumns = @JoinColumn(name = "air_company_id")
    )
    private List<AirCompany> company;

    @ManyToOne(cascade ={CascadeType.REFRESH})
    @JoinColumn(name = "departure_point")
    private AirPort departurePoint;

    @ManyToOne(cascade ={CascadeType.REFRESH})
    @JoinColumn(name = "destination_point")
    private AirPort destinationPoint;

    @Column(name = "distance")
    private int distance;

    @Column(name = "price")
    private int price;

    public AviaRace() { }

    public AviaRace(List<AirCompany> company, AirPort departurePoint, AirPort destinationPoint, int distance, int price, LocalTime time) {
        this.company = company;
        this.departurePoint = departurePoint;
        this.destinationPoint = destinationPoint;
        this.distance = distance;
        this.price = price;
        this.time = time;
    }

    public AviaRace(Long id, LocalDateTime created, LocalDateTime updated, AirPort departurePoint, AirPort destinationPoint, int distance, int price) {
        super(id, created, updated);
        this.departurePoint = departurePoint;
        this.destinationPoint = destinationPoint;
        this.distance = distance;
        this.price = price;
    }



    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public AirPort getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(AirPort departurePoint) {
        this.departurePoint = departurePoint;
    }

    public AirPort getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(AirPort destinationPoint) {
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

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<AirCompany> getCompany() {
        return company;
    }

    public void setCompany(List<AirCompany> company) {
        this.company = company;
    }
}
