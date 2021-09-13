package com.example.airline.dto;

public class AirPortDto extends AbstractDto {

    private Long id;
    private String name;

    public AirPortDto(String name) {
        this.name = name;
    }

    public AirPortDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
