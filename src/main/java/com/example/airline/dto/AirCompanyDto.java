package com.example.airline.dto;

public class AirCompanyDto extends AbstractDto {

    private Long id;
    private String name;


    public AirCompanyDto(String name) {
        this.name = name;
    }

    public AirCompanyDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
