package com.example.airline.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "airport")
public class AirPort extends AbstractEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;


    public AirPort(Long id, LocalDateTime created, LocalDateTime updated, String name) {
        super(id, created, updated);
        this.name = name;
    }

    public AirPort(String name) {
        this.name = name;
    }

    public AirPort() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
